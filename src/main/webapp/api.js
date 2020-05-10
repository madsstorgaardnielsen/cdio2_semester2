function addUser() {
    var userInfo = {}
    userInfo.userId = 0;
    userInfo.firstName = $("#firstname").val();
    userInfo.lastName = $("#lastname").val();
    /*userInfo.initials = $("#initials").val();*/
    userInfo.cpr = $("#cpr").val();
    /*userInfo.password = $("#password").val();*/
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
    // $('').append('html'), appends html to an html element.
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
        '<td class ="role">' + user.role + '</td>' +
        '<td class = firstName>' + user.firstName + '</td>' +
        '<td class = lastName>' + user.lastName + '</td>' +
        '<td class = ini>' + user.initials + '</td>' +
        '<td class = cpr>' + user.cpr + '</td>' +
        '<td class = password>' + user.password + '</td>' +
        '<td> <form action="javascript:deleteUser(' + user.userId + ')"> <button class="deletebutton">Delete</button> </form>  </td>' +
        '<td class = edit> <button class="editbutton">Edit</button> </td> </tr>'
}


function deleteUser(userId) {
    var answer = window.confirm("Vil du slette bruger med id: " + userId)
    if (answer) {
        Agent.postJson('rest/user/' + userId, 0, function (data) {
            loadUsers();
        }, function (xhr, statusmsg, errormsg) {
        });
        window.alert("Bruger slettet")
    }
}

function searchforUser() {
    $("#answer").empty();
    Agent.getJson('rest/user/' + $("#searchId").val(), function (data) {
            $("#answer").append(generateUserHTML(data))
        },
        function (data) {
            $("#answer").append(generateUserHTML(data))
        }
    )
}

function updateUser(userInfo) {
        Agent.putJson('rest/user', userInfo, function (data) {
            loadUsers();
        }, function () {
            window.alert("kunne ikke opdatere")
        });
}




