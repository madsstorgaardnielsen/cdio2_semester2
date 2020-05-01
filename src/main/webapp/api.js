function addUser() {
    var userInfo = {}
    userInfo.userId = $("#userid").val();
    userInfo.firstName = $("#firstname").val();
    userInfo.lastName = $("#lastname").val();
    userInfo.initials = $("#initials").val();
    userInfo.cpr = $("#cpr").val();
    userInfo.password = $("#password").val();
    userInfo.role = $("#roller").val();
    Agent.postJson('rest/user', userInfo, function (data) {
    }, function (xhr, statusmsg, errormsg) {
        alert(xhr.responseJSON.message);
    });
}