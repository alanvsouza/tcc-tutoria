var sobreClick = false;
var sobreMouse = false;
var sobreClick2 = false;
var sobreMouse2 = false;

var sobre = document.querySelector('li.sobre');
var sobre2 = document.querySelector('li.sobre2');
var sobreList = document.querySelector('div.items-sobre');
var sobreList2 = document.querySelector('div.items-sobre2');

if(sobre != null){
    sobre.addEventListener('mouseover', function () {
        sobreMouse = true;
        sobreList.style.visibility = 'visible';
        sobreList.style.opacity = '1';
        sobreList.style.display = "";
    });

    sobre.addEventListener('mouseout', function () {
        sobreMouse = false;
        sobreList.style.visibility = 'hidden';
        sobreList.style.opacity = '0';
    });

    sobreList.addEventListener('mouseover', function () {
        sobreMouse = true;
        sobreList.style.visibility = 'visible';
        sobreList.style.opacity = '1';
    });

    sobreList.addEventListener('mouseout', function () {
        sobreMouse = false;
        sobreList.style.visibility = 'hidden';
        sobreList.style.opacity = '0';
    });

    sobre.addEventListener('click', function () {

        if (sobreMouse == false) {

            if (sobreClick == true) {

                sobreList.style.visibility = 'hidden';
                sobreList.style.opacity = '0';
            } else {
                sobreList.style.visibility = 'visible';
                sobreList.style.opacity = '1';
            }

            if (sobreClick == true) {
                sobreClick = false;
            } else
                sobreClick = true;
        }

    });
}

if(sobre2 != null){
    sobre2.addEventListener('mouseover', function () {
        sobreMouse2 = true;
        sobreList2.style.visibility = 'visible';
        sobreList2.style.opacity = '1';
    });

    sobre2.addEventListener('mouseout', function () {
        sobreMouse2 = false;
        sobreList2.style.visibility = 'hidden';
        sobreList2.style.opacity = '0';
    });

    sobreList2.addEventListener('mouseover', function () {
        sobreMouse = true;
        sobreList2.style.visibility = 'visible';
        sobreList2.style.opacity = '1';
    });

    sobreList2.addEventListener('mouseout', function () {
        sobreMouse = false;
        sobreList2.style.visibility = 'hidden';
        sobreList2.style.opacity = '0';
    });

    sobre2.addEventListener('click', function () {

        if (sobreMouse2 == false) {

            if (sobreClick2 == true) {

                sobreList2.style.visibility = 'hidden';
                sobreList2.style.opacity = '0';
            } else {
                sobreList2.style.visibility = 'visible';
                sobreList2.style.opacity = '1';
            }

            if (sobreClick2 == true) {
                sobreClick2 = false;
            } else
                sobreClick2 = true;
        }
    });
}

imgUser = document.getElementById('icon-perfil');
if(imgUser != null){ 
    nav = document.getElementById('list-navbar');
    nav.style.margin  = "auto auto auto calc(100% - 690px)";
}