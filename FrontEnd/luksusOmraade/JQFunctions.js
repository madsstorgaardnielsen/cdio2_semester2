var JQFunctions = {}

JQFunctions.getJson = function(url, success, error){
    $.ajax({
        url:url,
        method: 'GET',
        contentType:'application/json',
        success:success,
        error:error
    })
}

JQFunctions.postJson = function(url, data, success, error){
    $.ajax({
        url:url,
        method: 'POST',
        data: JSON.stringify(data),
        success: success,
        contentType: 'application/json',
        error: error
    })
}

JQFunctions.putJson = function(url, data, success, error){
    $.ajax({
        url:url,
        method: 'PUT',
        data: JSON.stringify(data),
        success: success,
        contentType: 'application/json',
        error: error
    })
}

JQFunctions.delete = function(url, success, error) {
    $.ajax({
        url: url,
        method: 'DELETE',
        success: success,
        error: error

    })
}
