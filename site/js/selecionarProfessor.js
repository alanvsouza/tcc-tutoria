
const comboboxProfessores = document.querySelector('select#selecionar-tutor');
const submitComboboxButton = document.querySelector('input#select-professor-combobox');
// const options = document.getElementsByClassName('option-tutor');

comboboxProfessores.addEventListener('change', (event) => {
    submitComboboxButton.click();
});

// window.onload = initPage;

// function initPage(){
//     options[1].click();
//     submitComboboxButton.click();
// }
