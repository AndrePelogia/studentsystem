package br.com.cursojava.studentsystem.sistema.frontend.nota;

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
import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO;
import br.com.cursojava.studentsystem.sistema.backend.materia.model.MateriaPO;
import br.com.cursojava.studentsystem.sistema.backend.nota.controller.NotaFACADE;
import br.com.cursojava.studentsystem.sistema.backend.nota.model.NotaPO;
import br.com.cursojava.studentsystem.utilidades.Messages;
import br.com.cursojava.studentsystem.utilidades.Utilidades;

public class NotaAction extends DispatchAction{

	public ActionForward abrirTela( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		NotaForm meuForm = (NotaForm) form;
		meuForm.limparCampos();
		return filtrar( mapping, form, request, response );
	}

	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaForm meuForm = (NotaForm) form;
			NotaFACADE facade = new NotaFACADE();
			ArrayList< NotaPO > encontrados = facade.filtrar( montarPO( meuForm ) );
			meuForm.setNotas( encontrados );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "notaView" );
	}

	private NotaPO montarPO( NotaForm meuForm ) throws ParseException {

		NotaPO notaPO = new NotaPO();

		AlunoPO aluno = new AlunoPO();
		if ( meuForm.getIdAluno() != null && !meuForm.getIdAluno().isEmpty() ) {
			aluno.setId( Long.valueOf( meuForm.getIdAluno() ) );
		} else {
			aluno.setId( null );
		}

		notaPO.setAluno( aluno );

		if ( meuForm.getId() != null && !meuForm.getId().isEmpty() ) {
			notaPO.setId( Long.valueOf( meuForm.getId() ) );
		} else {
			notaPO.setId( null );
		}

		if ( meuForm.getNota() != null && !meuForm.getNota().isEmpty() ) {
			notaPO.setNota( Utilidades.parseBigDecimal( meuForm.getNota() ) );
		} else {
			notaPO.setNota( null );
		}

		if ( meuForm.getObservacao() != null && !meuForm.getObservacao().isEmpty() ) {
			notaPO.setObservacao( meuForm.getObservacao() );
		} else {
			notaPO.setObservacao( null );
		}

		if ( meuForm.getData() != null && !meuForm.getData().isEmpty() ) {
			notaPO.setData( Utilidades.parseDate( meuForm.getData() ) );
		} else {
			notaPO.setData( null );
		}

		MateriaPO materiaPO = new MateriaPO();
		if ( meuForm.getIdMateria() != null && !meuForm.getIdMateria().isEmpty() ) {
			materiaPO.setId( Long.valueOf( meuForm.getIdMateria() ) );
		} else {
			materiaPO.setId( null );

			if ( meuForm.getNome() != null && !meuForm.getNome().isEmpty() ) {
				materiaPO.setNome( meuForm.getNome() );
			} else {
				materiaPO.setNome( null );
			}

			if ( meuForm.getProfessor() != null && !meuForm.getProfessor().isEmpty() ) {
				materiaPO.setProfessor( meuForm.getProfessor() );
			} else {
				materiaPO.setProfessor( null );
			}
		}

		notaPO.setMateria( materiaPO );
		return notaPO;
	}

	public ActionForward inserir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {

			NotaForm meuForm = (NotaForm) form;

			NotaFACADE facade = new NotaFACADE();

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

			NotaForm meuForm = (NotaForm) form;

			NotaFACADE facade = new NotaFACADE();

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
		//return mapping.findForward( "alunoView" ); TELA QUE RETORNARIA, APÓS A REALIZAÇÃO DO MÉTODO!
		return filtrar( mapping, form, request, response );
	}

	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {

			NotaForm meuForm = (NotaForm) form;

			NotaFACADE facade = new NotaFACADE();

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

	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {
			NotaForm meuForm = (NotaForm) form;

			NotaFACADE facade = new NotaFACADE();

			//AQUI PEGA O GETID DO FORM (DADO DA TELA STRING) E TRANSFORMA COM VALUEOF EM UM LONG QUE IRÁ PRA O BACKEND
			NotaPO encontrado = facade.filtrarPorId( Long.valueOf( meuForm.getId() ) );

			meuForm.setId( encontrado.getId().toString() );
			meuForm.setNota( encontrado.getNota().toString() );
			meuForm.setObservacao( encontrado.getObservacao() );
			meuForm.setData( Utilidades.parseDate( encontrado.getData() ) );
			meuForm.setIdMateria( encontrado.getMateria().getId().toString() );
			meuForm.setNome( encontrado.getMateria().getNome() );
			meuForm.setProfessor( encontrado.getMateria().getProfessor() );
			meuForm.setIdAluno( encontrado.getAluno().getId().toString() );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "notaView" );
	}

	@SuppressWarnings( { "unchecked" } )
	public ActionForward selecionarMateriaAutoComplete( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		NotaForm meuForm = (NotaForm) form;
		try {

			JSONObject jsonObject = new JSONObject();
			JSONObject map = null;
			JSONArray jsonArray = new JSONArray();

			ArrayList< MateriaPO > materias = new ArrayList< MateriaPO >();

			materias = new NotaFACADE().filtrarMateriaPorNome( meuForm.getNome() );

			for ( MateriaPO materia : materias ) {

				map = new JSONObject();
				map.put( "value", materia.getNome() );
				map.put( "data", materia.getId() );
				map.put( "professor", materia.getProfessor() );

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
