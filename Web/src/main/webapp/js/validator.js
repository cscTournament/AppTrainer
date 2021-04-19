function checkData(formID) {
    var pattern = null;

    switch(formID){
        case 'first_name':
            pattern =  new RegExp('([A-Z]?[a-z]{1,10})|([А-ЯЁ]?[а-яё]{1,10})');
            break;

        case 'last_name':
            pattern =  new RegExp('([A-Z]?[a-z]{1,10})|([А-ЯЁ]?[а-яё]{1,10})');
            break;

        case 'login':
            pattern =  new RegExp('[a-zA-Z0-9]{3,6}', '');
            break;

        case 'password':
            pattern =  new RegExp('[a-zA-Z0-9._*]{3,6}');
            break;

        case 'balance':
            pattern =  new RegExp('[0-9]{1,6}');
            break;
    }

    inputStr = document.getElementById(formID + 'Form').value;

    if(pattern.test(inputStr)) {
        document.getElementById(formID).style.color = 'green';
    } else {
        document.getElementById(formID).style.color = 'red';
    }
}

function clearField(msg,formID) {
    var str = document.getElementById(formID).value;
    if(str == msg)
        document.getElementById(formID).value='';
}

function restoreField(msg,formID) {
    var str = document.getElementById(formID).value;
    if(str == '')
        document.getElementById(formID).value=msg;
}

