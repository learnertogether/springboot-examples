$.ajax({
    url: "/login/getusers",
    type: "post",
    dataType: "json",
    success: function (data) {
        if (data.success) {
        }
    }
});