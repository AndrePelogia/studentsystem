<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Study System</title>

<!-- IMPORTE CSS -->
<jsp:include page="../../template/imports/importscss.jsp"></jsp:include>
</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">

			<!-- ================= INICIO CABEÇALHO ==================== -->

			<jsp:include page="../../template/cabecalho.jsp"></jsp:include>

			<!-- ================= FIM CABEÇALHO ==================== -->


			<!-- ================= INICIO MENU ==================== -->
			<jsp:include page="../../template/menu.jsp"></jsp:include>
			<!-- ================= FIM MENU ==================== -->

		</nav>

		<div id="page-wrapper">

			<!-- AQUI INICIA-SE A MONTAGEM DA TELA -->
			<!-- UTILIZA-SE O CONCEITO DE LINHA(ROW) E COLUNA(COL-??-0A12) -->
			<div class="row">
				<div class="col-xs-12">

					<!-- AQUI FAREMOS NOSSA TELA -->
					<h2 class="page-header">
						<i class="fa fa-book"></i>
						Turma
						<small style="font-size: 45%">
							<i class="fa fa-angle-double-right"></i>
							Cadastro de Turmas
						</small>
					</h2>

				</div>
			</div>
			<!-- /.row -->

			<!-- TELA -->
			<div class="row">
				<div class="col-xs-12">
					<!-- CONFIGURAÇÃO IMPORTANTE PARA O FUNCIONAMENTO DO STRUTS (html:form) -->
					<!-- Sempre que o form sofrer um SUBMIT, ele executará o action -->
					<html:form styleId="form_turma" action="turmaAction.do" method="post">
						<html:hidden styleId="method" property="method" value="nada" />
						<div class="panel panel-primary">
							<div class="panel-heading">
								<logic:messagesPresent message="false">
									<div class="alert alert-danger">
										<html:messages id="message" message="false">
											<bean:write filter='false' name='message' />
										</html:messages>
									</div>
								</logic:messagesPresent>
								<logic:messagesPresent message="true">
									<div class="alert alert-success">
										<html:messages id="message" message="true">
											<bean:write filter='false' name='message' />
										</html:messages>
									</div>
								</logic:messagesPresent>
							</div>

							<div class="panel-body">
								<!-- CRIACAO DOS CAMPOS JUNTAMENTE COM SUAS LABELS -->
								<div class="row">
									<div class="form-group col-xs-12">
										<span>*Campos em vermelho são obrigatórios!</span>
									</div>
									<div class="form-group col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<label for="id">Código</label>
										<html:text styleId="id" styleClass="form-control text-center bloqueado" property="id" name="turmaForm"></html:text>
									</div>

									<!-- UTILIZADO PARA QUEBRAR LINHA -->
									<div class="clearfix"></div>

									<div class="form-group col-xs-6 col-sm-6 col-md-4 col-lg-4">
										<label for="nome">Nome</label>
										<html:text styleId="nome" styleClass="form-control text-center obrigatorio" property="nome" name="turmaForm"></html:text>
									</div>
									<div class="clearfix"></div>
									<div class="form-group col-xs-6  col-sm-6 col-md-4 col-lg-4 ">
										<label for="dataInicio">Data de Início</label>
										<html:text styleId="dataInicio" styleClass="form-control text-center obrigatorio data" property="dataInicio" name="turmaForm"></html:text>
									</div>
									<div class="clearfix"></div>
									<div class="form-group col-xs-6  col-sm-6 col-md-4 col-lg-4 ">
										<label for="dataTerminio">Data de Término</label>
										<html:text styleId="dataTerminio" styleClass="form-control text-center obrigatorio data" property="dataTerminio" name="turmaForm"></html:text>
									</div>

									<!-- <div class="clearfix"></div> -->


									<!-- TABELA DE DADOS  -->
									<div class="row">
										<div class="table-responsive col-xs-12 col-md-offset-1 col-sm-12 col-md-10 col-ls-12" style="margin-top: 20px;">
											<table class="table table-bordered table-striped table-hover">
												<thead>
													<tr class="bg-primary">
														<th class="text-center">#</th>
														<th>Nome</th>
														<th>Data de Início</th>
														<th>Data de Término</th>
														<th class="text-center">Selecionar</th>
													</tr>
												</thead>
												<!-- PROPRIEDADES:
         									  	   id - Objeto corrente do FOR
										           indexId - é o contador como por exemplo o (i)
										           name - Nome do Form onde a lista esta
										           property - Nome do atributo que representa a lista
										           type = Tipo do Objeto que esta dentro(ArrayList<type>) da lista -->
												<logic:iterate id="turmaCorrente" name="turmaForm" indexId="i" property="turmas" type="br.com.cursojava.studentsystem.sistema.backend.turma.model.TurmaPO">
													<tr>
														<td class="text-center">${turmaCorrente.id}</td>
														<td>${turmaCorrente.nome}</td>
														<td>${turmaCorrente.dataInicio}</td>
														<td>${turmaCorrente.dataTermino}</td>

														<td class="text-center">
															<a href="${rootWeb}/turmaAction.do?method=selecionar&id=${turmaCorrente.id}">
																<i class="glyphicon glyphicon-edit btn btn-xs btn-success"></i>

															</a>
														</td>
													</tr>
												</logic:iterate>
											</table>
										</div>
									</div>
								</div>
							</div>

						</div>




						<div class="panel-footer">
							<div class="row">
								<logic:empty property="id" name="turmaForm">
									<div class="form-group col-xs-6 col-sm4 col-md-3 col-lg-3">
										<button type="submit" id="inserir" class="btn btn-success btn-block">
											<i class="glyphicon glyphicon-floppy-save"></i>
											Inserir
										</button>
									</div>
								</logic:empty>

								<logic:notEmpty property="id" name="turmaForm">
									<div class="form-group col-xs-6 col-sm4 col-md-3 col-lg-3">
										<button type="submit" id="alterar" class="btn btn-primary btn-block">
											<i class="glyphicon glyphicon-retweet"></i>
											Alterar
										</button>
									</div>



									<div class="form-group col-xs-6 col-sm4 col-md-3 col-lg-3">
										<button type="submit" id="excluir" class="btn btn-danger btn-block">
											<i class="glyphicon glyphicon-remove"></i>
											Excluir
										</button>
									</div>
								</logic:notEmpty>

								<div class="form-group col-xs-6 col-sm4 col-md-3 col-lg-3">
									<button type="button" id="pesquisar" class="btn btn-info btn-block">
										<i class="fa fa-search"></i>
										Pesquisar
									</button>
								</div>

								<div class="form-group col-xs-6 col-sm4 col-md-3 col-lg-3">
									<button type="button" id="limpar" class="btn btn-warning btn-block">
										<i class="glyphicon glyphicon-erase"></i>
										Limpar
									</button>
								</div>
							</div>
						</div>
						<!-- FOOTER -->
					</html:form>
				</div>
			</div>
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->


	<!-- IMPORTE JS -->
	<jsp:include page="../../template/imports/importsjs.jsp"></jsp:include>

	<script type="text/javascript">
		/* 
		   EXECUTADO NA CARGA DA PAGINA 
		   Trabalhando com JQuery para manipular os componentes
		   # pega os elementos pelo ID - $("#nome")
		   . pega os elementos por CLASS - $(".bloqueado")
		   attr serve para adicionar atributos em tempo de execução
		   css serve para estilizar campos etc...
		 */
		$(document).ready(function() {
			/* Bem vindo ao ambiente JQuery */

			/* Setando o foco inicial */
			$("#nome").focus();

			/* Desliga o auto-complete da página */
			$("#form_turma").attr("autocomplete", "off");

			/* Criando uma estilização para campos Bloqueados  */
			$(".bloqueado").attr("readonly", "readonly");
			$(".bloqueado").css("font-weight", "bold");
			$(".bloqueado").css("cursor", "not-allowed");

			/* Criando uma estilização para campos Obrigatórios  */
			$(".obrigatorio").attr("required", "required");
			$(".obrigatorio").css("border-color", "red");

			/* Definindo o tamanho maximo dos campos */
			$("#nome").attr("maxlength", "80");

			$("#dataInicio").attr("maxlength", "10");
			$("#dataTermino").attr("maxlength", "10");

			/* DEFININDO AS MASCARAS */
			$(".data").mask('00/00/0000', {
				placeholder : "__/__/_____",
				reverse : true
			});

			$(".decimal").mask('##0,00', {
				placeholder : "0,00",
				reverse : true
			});

			/* DEFININDO OS EVENTOS DOS BOTOES */
			$("#inserir").click(function() {
				$("#method").val("inserir");
			});

			$("#alterar").click(function() {
				$("#method").val("alterar");
			});

			$("#excluir").click(function() {
				$("#method").val("excluir");
				$("#form_turma").submit();
			});

			$("#pesquisar").click(function() {
				$("#method").val("filtrar");
				$("#form_turma").submit();
			});

			$("#limpar").click(function() {
				$("#method").val("abrirTela");
				$("#form_turma").submit();
			});

		});
	</script>
</body>
</html>