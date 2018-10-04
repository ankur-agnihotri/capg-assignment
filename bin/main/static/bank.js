var rootURL = 'http://localhost:8080/bank'
function findByCustomerId() {
		var id = $('#customerIDForDetail').val()
    $.ajax({
        type: 'GET',
        url: rootURL + '/' +'customer/' +id,
        dataType: "json",
        success: function(data){
           createHtmlFromJson(data);
        }
    });
}

function addAccount() {
    console.log('addAccount');
    var id = $('#customerID').val()
    var credit = $('#credit').val()
    
    console.log('id '+id+'credit '+credit );
    
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: rootURL+ '/' +'account/' +id+'/'+credit,
        dataType: "json",
      
        success: function(data){
            $('#output').append("Current Account No: "+data);
        },
        error: function(){
            alert(' error: ');
        }
    });
}

function createHtmlFromJson(data){
	var txt = "";
	
	txt= "customerID: "+ data.customerID + " First Name: "+ data.firstName + " Last Name: "+data.lastName;
	txt = txt+"<br><br><table border=\"1\">"
	var currentAccountList = data.currentAccountList;
	var accountLnth=currentAccountList.length;
	 if(accountLnth > 0){
        var accountTxt="";
		 for(var i=0;i<accountLnth;i++){
             	var accountRow =  "<tr><td> Current Account No: </td><td>"+currentAccountList[i].currentAccountNumber+"</td><td> Balance: </td><td>"+currentAccountList[i].balance+"</td></tr>";
             
            	var transactions =  currentAccountList[i].transactionList;
            	if(transactions!=null){
            	var transactionsLen = transactions.length;
            	if(transactionsLen > 0){
            		var transactionTxt = "<br><table border=\"1\">";
            		for(var j=0;j<transactionsLen;j++){
            			transactionTxt += "<tr><td>Transaction Type: </td><td>"+transactions[j].transactionType + "</td><td> Amount: </td><td>"+ transactions[j].transactionAmount
            		}
            		transactionTxt=transactionTxt+"</table><br>";
            		accountRow = accountRow +"<tr>"+  transactionTxt+"<tr>";
            		
            	}
         }
            	accountTxt = accountTxt+accountRow;
            	}
		 txt = txt + accountTxt +"</table>"
	 }
	 
	 $("#detaildiv").append(txt).css('display', '');
}