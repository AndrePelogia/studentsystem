<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<!-- TAGS PARA O USO DO STRUTS NO JSP -->
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

<title>Student System</title>

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
						<i class="glyphicon glyphicon-education"></i>
						Aluno
						<small style="font-size: 45%">
							<i class="fa fa-angle-double-right"></i>
							Cadastro de Alunos
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
					<html:form styleId="form_aluno" action="alunoAction.do" method="post">
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
										<html:text styleId="id" styleClass="form-control text-center bloqueado" property="id" name="alunoForm"></html:text>
									</div>
									<div class="clearfix"></div>
									<div class="form-group col-xs-12 col-sm-6 col-md-8 col-lg-8">
										<label for="nome">Nome</label>
										<html:text styleId="nome" styleClass="form-control text-center obrigatorio" property="nome" name="alunoForm"></html:text>
									</div>

									<div class="form-group col-xs-6 col-sm-6 col-md-4 col-lg-4">
										<label for="sexo">Sexo</label>
										<html:select styleId="sexo" styleClass="form-control text-center obrigatorio" property="sexo" name="alunoForm">
											<html:option value="">Selecione</html:option>
											<html:option value="Masculino">Masculino</html:option>
											<html:option value="Feminino">Feminino</html:option>
										</html:select>
									</div>

									<div class="form-group col-xs-6 col-sm-6 col-md-4 col-lg-4 ">
										<label for="dataNascimento">Data de Nascimento</label>
										<html:text styleId="dataNascimento" styleClass="form-control text-center obrigatorio data" property="dataNascimento" name="alunoForm"></html:text>
									</div>

									<div class="form-group col-xs-6 col-sm-6 col-md-4 col-lg-4 ">
										<label for="peso">Peso</label>
										<html:text styleId="peso" styleClass="form-control text-center decimal" property="peso" name="alunoForm"></html:text>
									</div>

									<div class="form-group col-xs-6 col-sm-6 col-md-4 col-lg-4">
										<label for="altura">Altura</label>
										<html:text styleId="altura" styleClass="form-control text-center decimal" property="altura" name="alunoForm"></html:text>
									</div>
									
									<!-- O CAMPO CPF POSSUI UMA LÓGICA QUE: SE O ID ESTIVER CHEIO ELE MOSTRA O CAMPO BLOQUADO, SENÃO, SE O ID ESTIVER VAZIO
									MOSTRA DESBLOQUADO -->

									<logic:empty property="id" name="alunoForm">
										<div class="form-group col-xs-6 col-sm-6 col-md-4 col-lg-4 ">
											<label for="cpf">CPF</label>
											<html:text styleId="cpf" styleClass="form-control text-center obrigatorio cpf" property="cpf" name="alunoForm"></html:text>
										</div>
									</logic:empty>

									<logic:notEmpty property="id" name="alunoForm">
										<div class="form-group col-xs-6 col-sm-6 col-md-4 col-lg-4 ">
											<label for="cpf">CPF</label>
											<html:text styleId="cpf" styleClass="form-control text-center bloqueado obrigatorio cpf" property="cpf" name="alunoForm"></html:text>
										</div>
									</logic:notEmpty>




									<div class="form-group col-xs-6 col-sm-6 col-md-4 col-lg-4 ">
										<label for="ra">RA</label>
										<html:text styleId="ra" styleClass="form-control text-center obrigatorio" property="ra" name="alunoForm"></html:text>
									</div>

									<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-4 ">
										<label for="turma">Turma</label>
										<html:text styleId="turma" styleClass="form-control text-center obrigatorio" property="turma" name="alunoForm"></html:text>
									</div>

									<!--  BLOCO DO ENDEREÇO-->
									<div class="col-xs-offset-1 col-xs-10 col-sm-offset-1 col-sm-10 col-md-offset-1 col-md-10  col-lg-offset-1 col-lg-10" style="border: 1px solid gray;">
										<h3 style="text-align: center;">Endereço:</h3>
										<div class="row">
											<div class="form-group col-xs-12 col-sm-10 col-md-6 col-lg-6">
												<label for="logradouro">Logradouro</label>
												<html:text styleClass="form-control" styleId="logradouro" name="alunoForm" property="logradouro" />
											</div>
											<div class="form-group col-xs-4 col-sm-6 col-md-2 col-lg-2">
												<label for="numero">Nº</label>
												<html:text styleClass="form-control" styleId="numero" name="alunoForm" property="numero" />
											</div>
											<div class="form-group col-xs-8 col-sm-6 col-md-4 col-lg-4">
												<label for="bairro">Bairro</label>
												<html:text styleClass="form-control" styleId="bairro" name="alunoForm" property="bairro" />
											</div>
											<div class="form-group col-xs-12 col-sm-4 col-md-6 col-lg-6">
												<label for="cidade">Cidade</label>
												<html:text styleClass="form-control" styleId="cidade" name="alunoForm" property="cidade" />
											</div>
											<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<label for="cep">CEP</label>
												<html:text styleClass="form-control cep" styleId="cep" name="alunoForm" property="cep" />
											</div>
											<div class="form-group col-xs-8 col-sm-6 col-md-2 col-lg-2">
												<label for="uf">UF</label>
												<html:select styleClass="form-control" styleId="uf" name="alunoForm" property="uf">
													<html:option value="">Selecione</html:option>
													<html:option value="SP">SP</html:option>
													<html:option value="PR">PR</html:option>
													<html:option value="AC">AC</html:option>
													<html:option value="AL">AL</html:option>
													<html:option value="AP">AP</html:option>
													<html:option value="AM">AM</html:option>
													<html:option value="BA">BA</html:option>
													<html:option value="CE">CE</html:option>
													<html:option value="DF">DF</html:option>
													<html:option value="ES">ES</html:option>
													<html:option value="GO">GO</html:option>
													<html:option value="MA">MA</html:option>
													<html:option value="MT">MT</html:option>
													<html:option value="MS">MS</html:option>
													<html:option value="MG">MG</html:option>
													<html:option value="PB">PB</html:option>
													<html:option value="PA">PA</html:option>
													<html:option value="PE">PE</html:option>
													<html:option value="PI">PI</html:option>
													<html:option value="RJ">RJ</html:option>
													<html:option value="RN">RN</html:option>
													<html:option value="RS">RS</html:option>
													<html:option value="RO">RO</html:option>
													<html:option value="RR">RR</html:option>
													<html:option value="SC">SC</html:option>
													<html:option value="SE">SE</html:option>
													<html:option value="TO">TO</html:option>
												</html:select>
											</div>
										</div>
										<!--  FECHA O BLOCO DO ENDEREÇO-->
									</div>

									<!-- TABELA DE DADOS  -->
									<div class="row">
										<div class="table-responsive col-xs-12" style="margin-top: 20px;">
											<table class="table table-bordered table-striped table-hover">
												<thead>
													<tr class="bg-primary">
														<th class="text-center">#</th>
														<th>Nome</th>
														<th>Sexo</th>
														<th>Turma</th>
														<th class="text-center">Selecionar</th>
													</tr>
												</thead>
												<!-- PROPRIEDADES:
         									  	   id - Objeto corrente do FOR
										           indexId - é o contador como por exemplo o (i)
										           name - Nome do Form onde a lista esta
										           property - Nome do atributo que representa a lista
										           type = Tipo do Objeto que esta dentro(ArrayList<type>) da lista -->
												<logic:iterate id="alunoCorrente" name="alunoForm" indexId="i" property="alunos" type="br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO">
													<tr>
														<td class="text-center">${alunoCorrente.id}</td>
														<td>${alunoCorrente.nome}</td>
														<td>${alunoCorrente.sexo}</td>
														<td>${alunoCorrente.turma}</td>

														<td class="text-center">
															<a href="${rootWeb}/alunoAction.do?method=selecionar&id=${alunoCorrente.id}">
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
								<logic:empty property="id" name="alunoForm">
									<div class="form-group col-xs-6 col-sm4 col-md-3 col-lg-3">
										<button type="submit" id="inserir" class="btn btn-success btn-block">
											<i class="glyphicon glyphicon-floppy-save"></i>
											Inserir
										</button>
									</div>
								</logic:empty>

								<logic:notEmpty property="id" name="alunoForm">
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
			$("#form_aluno").attr("autocomplete", "off");

			/* Criando uma estilização para campos Bloqueados  */
			$(".bloqueado").attr("readonly", "readonly");
			$(".bloqueado").css("font-weight", "bold");
			$(".bloqueado").css("cursor", "not-allowed");

			/* Criando uma estilização para campos Obrigatórios  */
			$(".obrigatorio").attr("required", "required");
			$(".obrigatorio").css("border-color", "red");

			/* Definindo o tamanho maximo dos campos */
			$("#nome").attr("maxlength", "80");
			$("#cpf").attr("maxlength", "14");
			$("#altura").attr("maxlength", "4");
			$("#ra").attr("maxlength", "8");
			$("#dataNascimento").attr("maxlength", "10");
			$("#turma").attr("maxlength", "50");
			$("#peso").attr("maxlength", "6");
			$("#logradouro").attr("maxlength", "60");
			$("#numero").attr("maxlength", "4");
			$("#bairro").attr("maxlength", "30");
			$("#cidade").attr("maxlength", "30");
			$("#cep").attr("maxlength", "11");

			/* Setando o Placeholder dos campos  */
			$("#nome").attr("placeholder", "Seu nome aqui!");
			$("#ra").attr("placeholder", "Seu RA aqui!");
			$("#turma").attr("placeholder", "Sua turma aqui!");

			$("#logradouro").attr("placeholder", "Ex.:Rua, AV, alameda...");
			$("#numero").attr("placeholder", "número");
			$("#bairro").attr("placeholder", "Bairro, vila...");
			$("#cidade").attr("placeholder", "Cidade");
			$("#cep").attr("placeholder", "CEP");

			/* DEFININDO AS MASCARAS */
			$(".cpf").mask('000.000.000-00', {
				placeholder : "000.000.000-00",
				clearIfNotMatch : true
			});

			$(".cep").mask('00.000-000', {
				placeholder : "00.000-000",
				clearIfNotMatch : true
			});

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
				$("#form_aluno").submit();
			});

			$("#pesquisar").click(function() {
				$("#method").val("filtrar");
				$("#form_aluno").submit();
			});

			$("#limpar").click(function() {
				$("#method").val("abrirTela");
				$("#form_aluno").submit();
			});

		});
	</script>
</body>

</html>
