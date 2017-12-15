indexApp
    .filter("dateFormat", function () {//时间格式化过滤器
        return function (input) {
            var out = input;
            out = new Date(input).format("yyyy-MM-dd hh:mm");
            return out;
        };
    })
    .filter("imageUrlVersion", function () {//图片版本过滤器
        return function (input) {
            var out = input;
            out = new Date().format("yyyy-MM-dd hh:mm:ss");
            return input + "?" + out;
        };
    })
    .filter("payModeFormat", function () {//支付方式过滤器
        return function (input) {
            var out = input;
            if(input == 0) {
                out = '在线支付'
            } else if (input == 1) {
                out = '货到付款'
            }
            return out;
        };
    })
    .filter("orderStatusFormat", function () {//订单状态过滤器
        return function (input) {
            var out = input.status;
            switch (input.status) {
                case 0:
                    out = '未付款'
                    break;
                case 1:
                    if(input.payMode == 0) {
                        out = '已付款'
                    } else {
                        out = '货到付款'
                    }
                    break;
                case 2:
                    out = '未发货'
                    break;
                case 3:
                    out = '配送中'
                    break;
                case 4:
                    out = '已收货'
                    break;
                case 5:
                    out = '已完成'
                    break;
            }
            return out;
        };
    })
    .filter("startDateFormat", function () {//租借起始时间过滤器
        return function (input) {
            var out = input;
            out = new Date(input).format("yyyy-MM-dd");
            return out;
        };
    })
    .filter("statusFormat", function () {//状态过滤器
        return function (input) {
            var out = input;
            if (input == null) {
                out = "待审核";
            } else if (input == 1) {
                out = "审核通过";
            } else {
                out = "审核未通过";
            }
            return out;
        };
    })
   