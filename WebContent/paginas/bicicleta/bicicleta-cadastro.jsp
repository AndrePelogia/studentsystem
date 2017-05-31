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
						<i class="fa fa-bicycle"></i>
						Bicicleta
						<small style="font-size: 45%">
							<i class="fa fa-angle-double-right"></i>
							Cadastro de Bicicletas
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
					<html:form styleId="form_bicicleta" action="bicicletaAction.do" method="post">
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
										<html:text styleId="id" styleClass="form-control text-center bloqueado" property="id" name="bicicletaForm"></html:text>
									</div>

									<!-- UTILIZADO PARA QUEBRAR LINHA -->
									<div class="clearfix"></div>

									<div class="form-group col-xs-6 col-sm-6 col-md-4 col-lg-4">
										<label for="marca">Marca</label>
										<html:text styleId="marca" styleClass="form-control text-center obrigatorio" property="marca" name="bicicletaForm"></html:text>
									</div>

									<div class="form-group col-xs-6 col-sm-6 col-md-4 col-lg-4">
										<label for="modalidade">Modalidade</label>
										<html:text styleId="modalidade" styleClass="form-control text-center obrigatorio" property="modalidade" name="bicicletaForm"></html:text>
									</div>

									<div class="form-group col-xs-6 col-sm-6 col-md-4 col-lg-4">
										<label for="cor">Cor</label>
										<html:text styleId="cor" styleClass="form-control text-center obrigatorio" property="cor" name="bicicletaForm"></html:text>
									</div>

									<div class="form-group col-xs-6 col-sm-6 col-md-4 col-lg-4">
										<label for="material">Material</label>
										<html:text styleId="material" styleClass="form-control text-center obrigatorio" property="material" name="bicicletaForm"></html:text>
									</div>

									<div class="form-group col-xs-6 col-sm-6 col-md-4 col-lg-4">
										<label for="marcha">Marcha</label>
										<html:select styleId="marcha" styleClass="form-control text-center obrigatorio" property="marcha" name="bicicletaForm">
											<html:option value="">Selecione</html:option>
											<html:option value="Sim">Sim</html:option>
											<html:option value="Não">Não</html:option>
										</html:select>
									</div>

									<div class="form-group col-xs-6 col-sm-6 col-md-4 col-lg-4">
										<label for="preco">Preço</label>
										<html:text styleId="preco" styleClass="form-control text-center obrigatorio decimal" property="preco" name="bicicletaForm"></html:text>
									</div>

									<div class="form-group col-xs-6  col-sm-6 col-md-4 col-lg-4 ">
										<label for="dataFabricacao">Data de Fabricação</label>
										<html:text styleId="dataFabricacao" styleClass="form-control text-center obrigatorio data" property="dataFabricacao" name="bicicletaForm"></html:text>
									</div>
									
									<div class="clearfix"></div>

									<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-8  ">
									<label for="idAluno">Aluno</label>
										<html:select styleClass="form-control text-center obrigatorio" styleId="aluno" property="idAluno" name="bicicletaForm">
											<html:optionsCollection name="bicicletaForm" property="comboAlunos" label="label" value="value" />
										</html:select>
									</div>

									<!-- TABELA DE DADOS  -->
									<div class="row">
										<div class="table-responsive col-xs-12 col-md-offset-1 col-sm-12 col-md-10 col-ls-12" style="margin-top: 20px;">
											<table class="table table-bordered table-striped table-hover">
												<thead>
													<tr class="bg-primary">
														<th class="text-center">#</th>
														<th>Marca</th>
														<th>Modalidade</th>
														<th>Cor</th>
														<th class="text-center">Selecionar</th>
													</tr>
												</thead>
												<!-- PROPRIEDADES:
         									  	   id - Objeto corrente do FOR
										           indexId - é o contador como por exemplo o (i)
										           name - Nome do Form onde a lista esta
										           property - Nome do atributo que representa a lista
										           type = Tipo do Objeto que esta dentro(ArrayList<type>) da lista -->
												<logic:iterate id="bicicletaCorrente" name="bicicletaForm" indexId="i" property="bicicletas" type="br.com.cursojava.studentsystem.sistema.backend.bicicleta.model.BicicletaPO">
													<tr>
														<td class="text-center">${bicicletaCorrente.id}</td>
														<td>${bicicletaCorrente.marca}</td>
														<td>${bicicletaCorrente.modalidade}</td>
														<td>${bicicletaCorrente.cor}</td>

														<td class="text-center">
															<a href="${rootWeb}/bicicletaAction.do?method=selecionar&id=${bicicletaCorrente.id}">
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
								<logic:empty property="id" name="bicicletaForm">
									<div class="form-group col-xs-6 col-sm4 col-md-3 col-lg-3">
										<button type="submit" id="inserir" class="btn btn-success btn-block">
											<i class="glyphicon glyphicon-floppy-save"></i>
											Inserir
										</button>
									</div>
								</logic:empty>

								<logic:notEmpty property="id" name="bicicletaForm">
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
			$("#marca").focus();

			/* Desliga o auto-complete da página */
			$("#form_bicicleta").attr("autocomplete", "off");

			/* Criando uma estilização para campos Bloqueados  */
			$(".bloqueado").attr("readonly", "readonly");
			$(".bloqueado").css("font-weight", "bold");
			$(".bloqueado").css("cursor", "not-allowed");

			/* Criando uma estilização para campos Obrigatórios  */
			$(".obrigatorio").attr("required", "required");
			$(".obrigatorio").css("border-color", "red");

			/* Definindo o tamanho maximo dos campos */
			$("#marca").attr("maxlength", "40");
			$("#cor").attr("maxlength", "30");
			$("#modalidade").attr("maxlength", "20");
			$("#preco").attr("maxlength", "9");
			$("#marcha").attr("maxlength", "3");
			$("#dataFabricacao").attr("maxlength", "11");
			$("#material").attr("maxlength", "30");

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
				$("#form_bicicleta").submit();
			});

			$("#pesquisar").click(function() {
				$("#method").val("filtrar");
				$("#form_bicicleta").submit();
			});

			$("#limpar").click(function() {
				$("#method").val("abrirTela");
				$("#form_bicicleta").submit();
			});

		});
	</script>
</body>
</html>