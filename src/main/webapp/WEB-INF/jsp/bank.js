var rootURL = 'http://localhost:8080/'
function findByCustomerId() {
		var id = $('#customerID').val
    $.ajax({
        type: 'GET',
        url: rootURL + '/' +'customer/' +id,
        dataType: "json",
        success: function(data){
            $('#btnDelete').show();
            renderDetails(data);
        }
    });
}

function addAccount() {
    console.log('addAccount');
    var id = $('#customerID').val
    var credit = $('#credit').val
    console.log('id '+id+'credit '+credit );
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: rootURL+ '/' +'account/' +id+'/'+credit,
        dataType: "json",
      
        success: function(data){
            alert('Account created successfully');
            $('#output').val(data);
        },
        error: function(){
            alert(' error: ');
        }
    });
}