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
const tutoriaAgendadaSucesso =
    document.querySelector('div#tutoria-agendada-sucesso') != null
        || document.querySelector('div#tutoria-agendada-falha') != null
        ? true : false;

if (!tutoriaAgendadaSucesso && !params.hasOwnProperty("professor")) {
    const submitComboboxButton = document.querySelector('input#select-professor-combobox');
    submitComboboxButton.click();
}


