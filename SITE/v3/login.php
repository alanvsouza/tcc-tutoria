<form id="loginModal" method="POST">

<div id="body-login" style="overflow-y:auto">

    <div id="header-login">
        <span id="btn-close-modal" class="material-icons" onclick="closeLogin()">close</span>
        <span id="loginTitle" >Login</span>
    </div> <!-- header-login -->

    <div id="conteudo-login" style="margin-top: -10px;">

        <div class="inputsLogin">
            <img alt=""> 
                <span  >Usuário</span>
                <input type="text" id="username"  placeholder="Username" label="Informe seu usuário">

                <span  >Senha</span>
                <input type="password" maxlength="15" style="margin-top: 15px;" id="password" placeholder="Informe sua senha">
        </div> <!-- inputs-login -->

        <input type="checkbox" name="" style="margin: 25px 0px 10px 11%;">
        <span style="margin: 0px 0px 10px 0px;">Manter dados salvos?</span>

    </div> <!-- conteudo-login -->
    
    <div id="footer-login">
        <input type="submit" id="confirmation" value="Acessar" >
        <p>Ainda não possui uma conta? Cadastre-se <a href="cadastro.php">aqui</a>.</p>
    </div> <!-- footer-login -->

</div> <!-- body-login -->

</form> <!-- loginModal -->

