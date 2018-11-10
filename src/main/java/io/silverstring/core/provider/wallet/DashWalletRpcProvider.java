package io.silverstring.core.provider.wallet;

import io.silverstring.core.provider.wallet.proxy.DashWalletRpcProxyProvider;
import io.silverstring.core.provider.wallet.proxy.SimpleBitcoinWalletRpcProxyProvider;
import io.silverstring.domain.dto.WalletDTO;
import io.silverstring.domain.enums.CategoryEnum;
import io.silverstring.domain.enums.CoinEnum;
import io.silverstring.domain.enums.WalletType;
import io.silverstring.domain.hibernate.Coin;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Service
public class DashWalletRpcProvider implements SimpleWalletRpcProvider {

    final DashWalletRpcProxyProvider dashWalletRpcProxyProvider;
    final ModelMapper modelMapper;

    @Autowired
    public DashWalletRpcProvider(DashWalletRpcProxyProvider dashWalletRpcProxyProvider, ModelMapper modelMapper) {
        this.dashWalletRpcProxyProvider = dashWalletRpcProxyProvider;
        this.modelMapper = modelMapper;
    }

    @Override
    public BigDecimal getMinFee() {
        return new BigDecimal(0.0001);
    }

    @Override
    public Coin getCoin() {
        Coin coin = new Coin();
        coin.setName(CoinEnum.DASH);
        return coin;
    }

    @Override
    public String getAccount(Long userId) {
        return SimpleWalletRpcProvider.ADDRESS_PREFIX + String.valueOf(userId);
    }

    @Override
    public String getSendAddressFromTxId(String txid, WalletDTO.TransactionInfo transactionInfo) {
        return transactionInfo.getAddress();
    }

    @Override
    public List<WalletDTO.TransactionInfo> getTransactions(CategoryEnum categoryEnum, Page page) {
        List<Map<String, Object>> transactions = dashWalletRpcProxyProvider.listtransactions(
                "*"
                , page.getPageSize()
                , page.getPageNo()
        );

        List<WalletDTO.TransactionInfo> transactionInfos = modelMapper.map(transactions, new TypeToken<List<WalletDTO.TransactionInfo>>(){}.getType());
        return transactionInfos.stream().filter(r -> {
            if (categoryEnum.equals(r.getCategory())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
    }

    @Override
    public WalletDTO.WalletCreateInfo createWallet(Long userId) {
        return WalletDTO.WalletCreateInfo.builder().address(
                dashWalletRpcProxyProvider.getaccountaddress(getAccount(userId))
        ).build();
    }

    @Override
    public BigDecimal getRealBalance(Long userId) {
        return new BigDecimal(String.valueOf(dashWalletRpcProxyProvider.getbalance(getAccount(userId))));
    }

    @Override
    public WalletDTO.TransactionInfo getTransaction(String txid) {
        Map<String, Object> transaction = dashWalletRpcProxyProvider.gettransaction(txid);
        return modelMapper.map(transaction, WalletDTO.TransactionInfo.class);
    }

    @Override
    public String sendTo(Long sendUserId, String fromAddress, String toaddress, Double amount, BigDecimal txFee) throws Exception {
        return dashWalletRpcProxyProvider.sendfrom(getAccount(sendUserId), toaddress, amount);
    }

    @Override
    public String sendFromHotWallet(String fromAddress, String toaddress, Double amount, BigDecimal txFee) throws Exception {
        return dashWalletRpcProxyProvider.sendfrom(WalletType.HOT.name(), toaddress, amount);
    }
}
