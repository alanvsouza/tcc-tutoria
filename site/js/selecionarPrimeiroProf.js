function getParams(url) {
    var params = {};
    var parser = document.createElement('a');
    parser.href = url;
    var query = parser.search.substring(1);
    var vars = query.split('&');
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split('=');
        params[pair[0]] = decodeURIComponent(pair[1]);
    }
    return params;
};

const params = getParams(window.location.href);

if (!params.hasOwnProperty("professor")) {
    console.log('entrou');

    const submitComboboxButton = document.querySelector('input#select-professor-combobox');
    submitComboboxButton.click();
}


