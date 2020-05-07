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

function loadUsers() {
    $("#userTableBody").empty();
    //Hints: $.each(data, function(i, element){ } iterates over a JSON-collection (data).
    // $('').append('html'), appends html to an html elemenut.
    Agent.getJson("rest/user",
        function (data) {
            $.each(data, function (i, element) {
                $("#userTable").append(generateUserHTML(element));
            });
        }, function (xhr, statusmsg, errormsg) {
        alert(xhr.responseJSON.message)
        }
    )


}


function generateUserHTML(user) {
    return '<tr> <td class = userId>' + user.userId + '</td>' +
        '<td class = role>' + user.role + '</td>' +
        '<td class = firstName>' + user.firstName + '</td>' +
        '<td class = lastName>' + user.lastName + '</td>' +
        '<td class = ini>' + user.initials + '</td>' +
        '<td class = cpr>' + user.cpr + '</td>' +
        '<td class = password>' + user.password + '</td>' +
        '<td> <form action="' +  + '"> <input type="submit" value="Delete"> </form>  </td>' +
        '<td> <form action="' +  + '"> <input type="submit" value="Edit"> </form> </td> </tr>'
}
