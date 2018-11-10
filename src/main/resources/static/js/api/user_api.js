function UserApi () {
    this.call = function(uri, callback, resultProcFunc, params) {
        $.ajax({
            url: uri
            , type: "POST"
            , dataType: 'json'
            , contentType:"application/json; charset=UTF-8"
            , data: JSON.stringify(params)
            , success: function (result) {
                resultProcFunc(result.data);
                return callback(result);
            }
            , error:function(e){
                //alert(e.responseText);
                //showAlert("ERROR : " + JSON.parse(e.responseText).message);
                var data = new Object();
                data.code = 1000;
                data.msg = JSON.parse(e.responseText).message;
                return callback(data);
            }
        });
        return true;
    };

    this.changePassword = function(callback, params) {
        return this.call("/api/user/changePassword", callback, function(data) {
        }, params);
    };

    this.releaseMember = function(callback, params) {
        return this.call("/api/user/releaseMember", callback, function(data) {
        }, params);
    };
}

var userApi = new UserApi();