<div id="navside">
    <ul class="list-navside">
        <li><i class="fas fa-calendar-alt"></i><a class="item-navside" href="index.php">Eventos</a></li>
        <li><i class="fas fa-users"></i><a style="cursor: pointer;" href="projetoTutoria.php"
                class="item-navside">Projeto Tutoria</a> </li>
        <li><i class="fas fa-user-clock"></i><a href="horariosTutor.php" class="item-navside">Horários Tutor</li></a>
        <li><i class="fas fa-user-friends"></i><a href="agendarTutoria.php" class="item-navside">Tutorias Agendadas</li>
        </a>
        <?php if (!isset($_SESSION['usuario'])) : ?>
        <li><i class="fas fa-user-circle"></i><a class="login item-navside" href="login.php">Login</a></li>
        <li><i class="fas fa-user-plus"></i><a href="cadastro.php" class="item-navside">Cadastro</a></li>
        <?php endif; ?>
    </ul>
</div>