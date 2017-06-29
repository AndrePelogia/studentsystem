package br.com.cursojava.studentsystem.sistema.frontend.aluno;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.aluno.controller.AlunoFACADE;
import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO;
import br.com.cursojava.studentsystem.sistema.backend.endereco.model.EnderecoPO;
import br.com.cursojava.studentsystem.sistema.backend.turma.controller.TurmaFACADE;
import br.com.cursojava.studentsystem.sistema.backend.turma.model.TurmaPO;
import br.com.cursojava.studentsystem.utilidades.Messages;
import br.com.cursojava.studentsystem.utilidades.Utilidades;

/**
 * 
 * Classe responsável por Gerenciar as ações da tela.
 * Todo método da ACTION possui os mesmos 4 parametros;
 * Todo método da ACTION deve retornar uma tela destino mapeada na
 * TAG <forward> do struts-config.xml
 *
 *
 * @author André Duarte <andre.silva141@fatec.sp.gov.br>
 * @since 19 de abr de 2017 20:18:12
 * @version 1.0
 */
public class AlunoAction extends DispatchAction{
	/**
	 * Método responsável por abrir a tela de cadastro de aluno.
	 * 
	 * Todo médodo da ACTION que será acessivel da tela terá
	 * o mesmo RETORNO(ActionForward), mesmos PARAMETROS(ActionMapping mapping,
	 * ActionForm form, HttpServletRequest request,
	 * HttpServletResponse response) e mesmo RETORNO(mapping.findForward( "nome_do_forward_aqui" ))
	 *
	 * @param ActionMapping mapping - Variavél que possibilita o ACESSO
	 *        á TAG <action-mappings> do struts-config.xml
	 * @param ActionForm form - Variavel que contem todos os dados vindos da tela setados em seus ATRIBUTOS
	 * @param HttpServletRequest request - Permite recuperar dados da tela sem que seja por intermedio do Struts
	 * @param HttpServletResponse response - Permite manipular a tela sem que seja por intermedio do Struts
	 * @return ActionForward - Forward referente a tela que deverá ser aberta apos a execução do Metodo. O valor passado como
	 *         parametro esta definido na propriedade NAME da TAG <forward> da TAG <action-mappings> do arquivo struts-config.xml.
	 *
	 * @author Gabriel Damiani Carvalheiro <gabriel.carvalheiro@gmail.com.br>
	 * @since 16 de jan de 2017 22:39:22
	 * @version 1.0
	 */
	public ActionForward abrirTela( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		AlunoForm meuForm = (AlunoForm) form;
		meuForm.limparCampos();

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, meuForm, request, response );
	}

	public ActionForward inserir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {

			AlunoForm meuForm = (AlunoForm) form;

			AlunoFACADE facade = new AlunoFACADE();

			facade.inserir( montarPO( meuForm ) );

			meuForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( ParseException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, form, request, response );
	}

	public ActionForward alterar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {

			AlunoForm meuForm = (AlunoForm) form;

			AlunoFACADE facade = new AlunoFACADE();

			facade.alterar( montarPO( meuForm ) );

			meuForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( ParseException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		//return mapping.findForward( "alunoView" );
		return filtrar( mapping, form, request, response );
	}

	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {

			AlunoForm meuForm = (AlunoForm) form;

			AlunoFACADE facade = new AlunoFACADE();

			facade.excluir( montarPO( meuForm ) );

			meuForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( ParseException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, form, request, response );
	}

	/**
	 * 
	 * Método responsável por filtrar a tabela de alunos que está mostrando na tela JSF
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 *
	 * @author André Duarte <andre.silva141@fatec.sp.gov.br>
	 * @since 7 de jun de 2017 17:52:02
	 * @version 1.0
	 */
	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {

			AlunoForm meuForm = (AlunoForm) form;

			AlunoFACADE facade = new AlunoFACADE();

			ArrayList< AlunoPO > encontrados = facade.filtrar( montarPO( meuForm ) );
			meuForm.setAlunos( encontrados );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( ParseException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "alunoView" );
	}

	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			AlunoForm meuForm = (AlunoForm) form;

			AlunoFACADE facade = new AlunoFACADE();

			AlunoPO encontrado = facade.filtrarPorId( Long.valueOf( meuForm.getId() ) );

			meuForm.setId( encontrado.getId().toString() );
			meuForm.setNome( encontrado.getNome() );
			meuForm.setCpf( encontrado.getCpf() );
			meuForm.setDataNascimento( Utilidades.parseDate( encontrado.getDataNascimento() ) );
			meuForm.setSexo( encontrado.getSexo() );
			meuForm.setRa( encontrado.getRa() );
			meuForm.setAltura( Utilidades.parseBigDecimal( encontrado.getAltura() ) );
			meuForm.setPeso( Utilidades.parseBigDecimal( encontrado.getPeso() ) );

			//ID DE TURMA QUE IRÁ PARA TELA DO ALUNO
			meuForm.setIdTurma( encontrado.getTurma().getId().toString() );

			meuForm.setIdEndereco( encontrado.getEndereco().getId().toString() );
			meuForm.setLogradouro( encontrado.getEndereco().getLogradouro() );
			meuForm.setNumero( encontrado.getEndereco().getNumero() );
			meuForm.setBairro( encontrado.getEndereco().getBairro() );
			meuForm.setCidade( encontrado.getEndereco().getCidade() );
			meuForm.setCep( encontrado.getEndereco().getCep() );
			meuForm.setUf( encontrado.getEndereco().getUf() );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "alunoView" );
	}

	private AlunoPO montarPO( AlunoForm meuForm ) throws ParseException {
		AlunoPO po = new AlunoPO();

		if ( meuForm.getId() != null && !meuForm.getId().isEmpty() ) {
			po.setId( Long.valueOf( meuForm.getId() ) );
		} else {
			po.setId( null );
		}

		if ( meuForm.getAltura() != null && !meuForm.getAltura().isEmpty() ) {
			po.setAltura( Utilidades.parseBigDecimal( meuForm.getAltura() ) );
		} else {
			po.setAltura( null );
		}

		if ( meuForm.getPeso() != null && !meuForm.getPeso().isEmpty() ) {
			po.setPeso( Utilidades.parseBigDecimal( meuForm.getPeso() ) );
		} else {
			po.setPeso( null );
		}

		if ( meuForm.getCpf() != null && !meuForm.getCpf().isEmpty() ) {
			po.setCpf( meuForm.getCpf() );
		} else {
			po.setCpf( null );
		}

		if ( meuForm.getDataNascimento() != null && !meuForm.getDataNascimento().isEmpty() ) {
			po.setDataNascimento( Utilidades.parseDate( meuForm.getDataNascimento() ) );
		} else {
			po.setDataNascimento( null );
		}

		if ( meuForm.getNome() != null && !meuForm.getNome().isEmpty() ) {
			po.setNome( meuForm.getNome() );
		} else {
			po.setNome( null );
		}

		if ( meuForm.getRa() != null && !meuForm.getRa().isEmpty() ) {
			po.setRa( meuForm.getRa() );
		} else {
			po.setRa( null );
		}

		if ( meuForm.getSexo() != null && !meuForm.getSexo().isEmpty() ) {
			po.setSexo( meuForm.getSexo() );
		} else {
			po.setSexo( null );
		}

		/** CAMPO DO ID DE TURMA NO ALUNO */
		TurmaPO turma = new TurmaPO();
		if ( meuForm.getIdTurma() != null && !meuForm.getIdTurma().isEmpty() ) {
			turma.setId( Long.valueOf( meuForm.getIdTurma() ) );
		} else {
			turma.setId( null );
		}

		if ( meuForm.getNomeTurma() != null && !meuForm.getNomeTurma().isEmpty() ) {
			turma.setNome( meuForm.getNome() );
		} else {
			turma.setNome( null );
		}

		po.setTurma( turma );
		/**
		 * Gerenciamento do Endereço
		 */
		EnderecoPO endereco = new EnderecoPO();

		if ( meuForm.getIdEndereco() != null && !meuForm.getIdEndereco().isEmpty() ) {
			endereco.setId( Long.valueOf( meuForm.getIdEndereco() ) );
		} else {
			endereco.setId( null );
		}

		if ( meuForm.getLogradouro() != null && !meuForm.getLogradouro().isEmpty() ) {
			endereco.setLogradouro( meuForm.getLogradouro() );
		} else {
			endereco.setLogradouro( null );
		}

		if ( meuForm.getNumero() != null && !meuForm.getNumero().isEmpty() ) {
			endereco.setNumero( meuForm.getNumero() );
		} else {
			endereco.setNumero( null );
		}

		if ( meuForm.getBairro() != null && !meuForm.getBairro().isEmpty() ) {
			endereco.setBairro( meuForm.getBairro() );
		} else {
			endereco.setBairro( null );
		}

		if ( meuForm.getCep() != null && !meuForm.getCep().isEmpty() ) {
			endereco.setCep( meuForm.getCep() );
		} else {
			endereco.setCep( null );
		}

		if ( meuForm.getCidade() != null && !meuForm.getCidade().isEmpty() ) {
			endereco.setCidade( meuForm.getCidade() );
		} else {
			endereco.setCidade( null );
		}

		if ( meuForm.getUf() != null && !meuForm.getUf().isEmpty() ) {
			endereco.setUf( meuForm.getUf() );
		} else {
			endereco.setUf( null );
		}

		po.setEndereco( endereco );

		return po;
	}

	@SuppressWarnings( { "unchecked" } )
	public ActionForward selecionarTurmaAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		AlunoForm meuForm = (AlunoForm) form;
		try {

			JSONObject jsonObject = new JSONObject();
			JSONObject map = null;
			JSONArray jsonArray = new JSONArray();

			ArrayList< TurmaPO > turmas = new ArrayList< TurmaPO >();
			TurmaPO turmaPO = new TurmaPO();

			turmaPO.setNome( meuForm.getNomeTurma() );
			turmas = ( new TurmaFACADE() ).filtrar( turmaPO );

			for ( TurmaPO turma : turmas ) {

				map = new JSONObject();
				map.put( "value", turma.getNome() );
				map.put( "data", turma.getId() );
				jsonArray.add( map );

			}

			jsonObject.put( "suggestions", jsonArray );

			response.setContentType( "application/json" );
			response.setHeader( "Cache-Control", "nocache" );
			response.getWriter().print( jsonObject.toString() );

		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
			e.printStackTrace();
		}

		return null;
	}
}
