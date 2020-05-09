function addUser() {
    var userInfo = {}
    userInfo.userId = 0;
    userInfo.firstName = $("#firstname").val();
    userInfo.lastName = $("#lastname").val();
    userInfo.initials = $("#initials").val();
    userInfo.cpr = $("#cpr").val();
    userInfo.password = $("#password").val();
    userInfo.role = $("#roller").val();
    Agent.postJson('rest/user', userInfo, function (data) {
        $("#Message").empty();
        $("#Message").append('<label>Bruger tilføjet</label>');
    }, function (xhr, statusmsg, errormsg) {
        $("#Message").empty();
        $("#Message").append('<label>Fejl opstod, bruger ikke tilføjet</label>');

        alert(xhr.responseJSON.message);
    });
}

function loadUsers() {
    $("#userTableBody").empty();
    //Hints: $.each(data, function(i, element){ } iterates over a JSON-collection (data).
    // $('').append('html'), appends html to an html elemenut.
    Agent.getJson("rest/user",
        function (data) {
            $.each(data, function () {
                $("#userTableBody").append(generateUserHTML(this));
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
        '<td> <form action="javascript:deleteUser(' + user.userId + ')"> <button type="submit">Delete</button> </form>  </td>' +
        '<td> <form> <button type="submit">Edit</button> </form> </td> </tr>'
}

function deleteUser(userId) {
    Agent.postJson('rest/user/'+ userId, 0, function(data) {
        loadUsers();
    }, function(xhr, statusmsg, errormsg) {
        })
}

function searchforUser() {
    $("#answer").empty();
    var searchObject = {}
    //if (($("#searchId").val()).localeCompare("undefined")){
        searchObject.userId = 13;
    //} else {searchObject.userId = $("#searchId").val();}
    searchObject.firstName = $("#searchFirstName").val();
    searchObject.lastName = $("#searchLastName").val();
    searchObject.initials = $("#searchIni").val();
    searchObject.cpr = "2738472691";
    searchObject.password = "qwerty";
    searchObject.role = $("#searchRole").val();


    Agent.getSearchJson('rest/user/1', searchObject, function(data) {
        $.each(data, function () {
        $("#answer").append(generateUserHTML(this))})},
        function () {
            $("#answer").append('<tr><td>Fail</td></tr>')}
)
}