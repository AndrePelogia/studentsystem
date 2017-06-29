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
						<i class="fa fa-pencil"></i>
						Nota
						<small style="font-size: 45%">
							<i class="fa fa-angle-double-right"></i>
							Cadastro de Notas
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
					
					<html:form styleId="form_nota" action="notaAction.do" method="post">
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
									
									<logic:notEmpty property="id" name="notaForm">
									<div class="form-group col-xs-4 col-sm-4 col-md-2 col-lg-2">
										<label for="id">Código</label>
										<html:text styleId="id" styleClass="form-control text-center bloqueado" property="id" name="notaForm"></html:text>
									</div>
									</logic:notEmpty>
									
									<div class="clearfix"></div>
									
									<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-8  ">
									<label for="idAluno">Aluno</label>
										<html:select styleClass="form-control text-center obrigatorio" styleId="aluno" property="idAluno" name="notaForm">
											<html:optionsCollection name="notaForm" property="comboAlunos" label="label" value="value" />
										</html:select>
									</div>
									
									<div class="form-group col-xs-6 col-sm-6 col-md-4 col-lg-4 ">
										<label for="nota">Nota</label>
										<html:text styleId="nota" styleClass="form-control text-center obrigatorio decimal" property="nota" name="notaForm"></html:text>
									</div>
									
									<div class="form-group col-xs-6 col-sm-6 col-md-12 col-lg-12 ">
										<label for="observacao">Observações</label>
										<html:text styleId="observacao" styleClass="form-control text-center " property="observacao" name="notaForm"></html:text>
									</div>
									
									<div class="form-group col-xs-6 col-sm-6 col-md-2 col-lg-2 ">
										<label for="data">Data</label>
										<html:text styleId="data" styleClass="form-control text-center obrigatorio data" property="data" name="notaForm"></html:text>
									</div>
									
									<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-4">
										<label for="nome">Materia</label>
										<html:text styleId="nome" styleClass="form-control obrigatorio text-center" property="nome" name="notaForm" />
										<i style="margin-top: -28px; float: right; margin-right: 8px; font-size: 25px;" class="fa fa-keyboard-o"></i>
										<html:hidden styleId="materiaSelecionada" property="idMateria" name="notaForm" />
									</div>
									
									<div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-6">
										<label for="professor">Professor</label>
										<html:text styleId="professor" styleClass="form-control text-center obrigatorio" property="professor" name="notaForm"></html:text>
									</div>
									
									<!-- TABELA DE DADOS  -->
									<div class="row">
										<div class="table-responsive col-xs-12" style="margin-top: 20px;">
											<table class="table table-bordered table-striped table-hover">
												<thead>
													<tr class="bg-primary">
														<th class="text-center">#</th>
														<th>Aluno</th>
														<th>Nota</th>
														<th>Materia</th>
														<th>Professor</th>
														<th class="text-center">Selecionar</th>
													</tr>
												</thead>
												
												<!-- PROPRIEDADES:
         									  	   id - Objeto corrente do FOR
										           indexId - é o contador como por exemplo o (i)
										           name - Nome do Form onde a lista esta
										           property - Nome do atributo que representa a lista
										           type = Tipo do Objeto que esta dentro(ArrayList<type>) da lista -->
										           
												<logic:iterate id="notaCorrente" name="notaForm" indexId="i" property="notas" type="br.com.cursojava.studentsystem.sistema.backend.nota.model.NotaPO">
													<tr>
														<td class="text-center">${notaCorrente.id}</td>
														<td>${notaCorrente.aluno.nome}</td>
														<td>${notaCorrente.nota}</td>
														<td>${notaCorrente.materia.nome}</td>
														<td>${notaCorrente.materia.professor}</td>

														<td class="text-center">
															<a href="${rootWeb}/notaAction.do?method=selecionar&id=${notaCorrente.id}">
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
								<logic:empty property="id" name="notaForm">
									<div class="form-group col-xs-6 col-sm4 col-md-3 col-lg-3">
										<button type="submit" id="inserir" class="btn btn-success btn-block">
											<i class="glyphicon glyphicon-floppy-save"></i>
											Inserir
										</button>
									</div>
								</logic:empty>

								<logic:notEmpty property="id" name="notaForm">
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
			$("#nota").focus();

			/* Desliga o auto-complete da página */
			$("#form_nota").attr("autocomplete", "off");

			/* Criando uma estilização para campos Bloqueados  */
			$(".bloqueado").attr("readonly", "readonly");
			$(".bloqueado").css("font-weight", "bold");
			$(".bloqueado").css("cursor", "not-allowed");

			/* Criando uma estilização para campos Obrigatórios  */
			$(".obrigatorio").attr("required", "required");
			$(".obrigatorio").css("border-color", "red");
			
			/* Definindo o tamanho maximo dos campos */
			$("#nota").attr("maxlength", "4");
			$("#observacao").attr("maxlength", "255");
			$("#data").attr("maxlength", "10");
			$("#materia").attr("maxlength", "50");
			$("#professor").attr("maxlength", "40");
			

	
		
			$(".data").mask('00/00/0000', {
				placeholder: "__/__/____",
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
				$("#form_nota").submit();
			});

			$("#pesquisar").click(function() {
				$("#method").val("filtrar");
				$("#form_nota").submit();
			});

			$("#limpar").click(function() {
				$("#method").val("abrirTela");
				$("#form_nota").submit();
			});

			$('#nome').autocomplete({
			    minChars : 2,
			    paramName : 'nome',
			    serviceUrl : '${rootWeb}/notaAction.do?method=selecionarMateriaAutoComplete',
			    onSelect : function(suggestion) {
			     $('#materiaSelecionada').val(suggestion.data);
			     $('#nome').val(suggestion.value);
			     $('#professor').val(suggestion.professor);
			    },
			    onSearchComplete : function(query, suggestions) {
			     if (suggestions == null || suggestions == '') {
			      $('#materiaSelecionada').val(null);
			      $('#nome').val(null);
			      $('#professor').val(null);
			     }
			    }
			   });
		});
	</script>
</body>

</html>