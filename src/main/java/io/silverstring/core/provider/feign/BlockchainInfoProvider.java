package io.silverstring.core.provider.feign;


import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.silverstring.core.config.FeignConfig;


@Service
@FeignClient(name = "blockchainInfoProvider", url = "https://blockchain.info", configuration = FeignConfig.class)
public interface BlockchainInfoProvider {

    @RequestMapping(method = RequestMethod.GET, value = "/ko/rawtx/{txHash}")
    Map<String, Object> getTransaction(@PathVariable("txHash") String txHash);
}
