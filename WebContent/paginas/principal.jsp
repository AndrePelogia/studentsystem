<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Student System</title>

<!-- IMPORTE CSS -->
<jsp:include page="../template/imports/importscss.jsp"></jsp:include>

</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">

			<!-- ================= INICIO CABEÇALHO ==================== -->

			<jsp:include page="../template/cabecalho.jsp"></jsp:include>

			<!-- ================= FIM CABEÇALHO ==================== -->


			<!-- ================= INICIO MENU ==================== -->
			<jsp:include page="../template/menu.jsp"></jsp:include>
			<!-- ================= FIM MENU ==================== -->

		</nav>

		<div id="page-wrapper">

			<!-- AQUI INICIA-SE A MONTAGEM DA TELA -->
			<!-- UTILIZA-SE O CONCEITO DE LINHA(ROW) E COLUNA(COL-??-0A12) -->
			<div class="row">
				<div class="col-xs-12">
					<h3>
						<i class="glyphicon glyphicon-education"></i>
						Sejam bem-vindos ao Study System
					</h3>

				</div>
			</div>
			<!-- /.row -->
			
			<!-- Para teste 
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-8  col-lg-offset-2 col-lg-8" style="border: 1px solid" color: "blue">
					<div class="row">
						<div class="form-group col-xs-12 col-sm-4 col-md-6 col-lg-8">
							<label>Nome</label>
							<input type="text" name="nome" class="form-control">
							
						</div>
							<div class="form-group col-xs-12 col-sm-4 col-md-8 col-lg-4">
							<label>Sexo</label>
							<input type="text" name="sexo" class="form-control">
							
						</div>
					</div>
				</div>
			</div>
			
			<div class="panel panel-success">
				<div class="panel-heading">
					Titulo da Tela
				</div>
				
				<div class="panel-body">
					<div class="row">
						<div class="form-group col-xs-12 col-sm-4 col-md-6 col-lg-8">
							<label>Nome</label>
							<input type="text" name="nome" class="form-control">
							
						</div>
							<div class="form-group col-xs-12 col-sm-4 col-md-8 col-lg-4">
							<label>Sexo</label>
							<input type="text" name="sexo" class="form-control">
							
						</div>
					</div>
				</div>
				
				
				<div class="panel-footer">
					Rodapé
				</div>-->
			</div>
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- IMPORTE JS -->
	<jsp:include page="../template/imports/importsjs.jsp"></jsp:include>


</body>

</html>
