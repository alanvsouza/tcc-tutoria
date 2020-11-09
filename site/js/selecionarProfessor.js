const comboboxProfessores = document.querySelector('select#selecionar-tutor');
const submitComboboxButton = document.querySelector('input#select-professor-combobox');

comboboxProfessores.addEventListener('change', (event) => {
    submitComboboxButton.click();
});
