<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<div class="navbar-default sidebar" role="navigation">
	<div class="sidebar-nav navbar-collapse">
		<ul class="nav" id="side-menu">
			<!-- <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            /input-group
                         </li>-->
			<li>
				<a href="${rootWeb}/index.html">
					<i class="fa fa-home"></i>
					Home
				</a>
			</li>
			<!-- MENU -->
			<li>
				<!-- SUBMENU -->
				<a href="#">
					<i class="fa fa-pencil-square-o"></i>
					Cadastro
					<span class="fa arrow"></span>
				</a>
				<!-- SUBMENU -->
				<ul class="nav nav-second-level">
					<li>
						<a href="${rootWeb}/alunoAction.do?method=abrirTela">
					<i class="glyphicon glyphicon-education"></i>
					Aluno
				</a>
					</li>
					<li>
						<!-- Realizando uma requizição via GET com STRUTS -->
				<a href="${rootWeb}/bicicletaAction.do?method=abrirTela">
					<i class="fa fa-bicycle"></i>
					Bicicleta
				</a>
					</li>
					<li>
						<!-- Realizando uma requizição via GET com STRUTS -->
				<a href="${rootWeb}/turmaAction.do?method=abrirTela">
					<i class="fa fa-book"></i>
					Turma
				</a>
					</li>
					
						<li>
						<!-- Realizando uma requizição via GET com STRUTS -->
				<a href="${rootWeb}/notaAction.do?method=abrirTela">
					<i class="fa fa-pencil"></i>
					Nota
				</a>
					</li>
					
				</ul>
				<!-- /.nav-second-level -->
			</li>

			<!-- Cada li é um Menu -->
			<%-- <li>
				<!-- Realizando uma requizição via GET com STRUTS -->
				<a href="${rootWeb}/alunoAction.do?method=abrirTela">
					<i class="glyphicon glyphicon-education"></i>
					Aluno
				</a>
			</li>
			<li>
				<!-- Realizando uma requizição via GET com STRUTS -->
				<a href="${rootWeb}/bicicletaAction.do?method=abrirTela">
					<i class="fa fa-bicycle"></i>
					Bicicleta
				</a>
			</li> --%>
		</ul>
	</div>
	<!-- /.sidebar-collapse -->
</div>
