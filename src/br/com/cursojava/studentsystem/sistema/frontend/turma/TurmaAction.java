package br.com.cursojava.studentsystem.sistema.frontend.turma;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.turma.controller.TurmaFACADE;
import br.com.cursojava.studentsystem.sistema.backend.turma.model.TurmaPO;
import br.com.cursojava.studentsystem.utilidades.Messages;
import br.com.cursojava.studentsystem.utilidades.Utilidades;

public class TurmaAction extends DispatchAction{

	public ActionForward abrirTela( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		TurmaForm meuForm = (TurmaForm) form;
		meuForm.limparCampos();

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, meuForm, request, response );
	}

	public ActionForward inserir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {

			TurmaForm meuForm = (TurmaForm) form;

			TurmaFACADE facade = new TurmaFACADE();

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

			TurmaForm meuForm = (TurmaForm) form;

			TurmaFACADE facade = new TurmaFACADE();

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

			TurmaForm meuForm = (TurmaForm) form;

			TurmaFACADE facade = new TurmaFACADE();

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

	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {

			TurmaForm meuForm = (TurmaForm) form;

			TurmaFACADE facade = new TurmaFACADE();

			ArrayList< TurmaPO > encontrados = facade.filtrar( montarPO( meuForm ) );
			meuForm.setTurmas( encontrados );

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
			TurmaForm meuForm = (TurmaForm) form;

			TurmaFACADE facade = new TurmaFACADE();

			TurmaPO encontrado = facade.filtrarPorId( Long.valueOf( meuForm.getId() ) );

			meuForm.setId( encontrado.getId().toString() );
			meuForm.setNome( encontrado.getNome() );
			meuForm.setDataInicio( Utilidades.parseDate( encontrado.getDataInicio() ) );
			meuForm.setDataTermino( Utilidades.parseDate( encontrado.getDataTermino() ) );

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

	private TurmaPO montarPO( TurmaForm meuForm ) throws ParseException {
		TurmaPO po = new TurmaPO();

		if ( meuForm.getId() != null && !meuForm.getId().isEmpty() ) {
			po.setId( Long.valueOf( meuForm.getId() ) );
		} else {
			po.setId( null );
		}

		if ( meuForm.getDataInicio() != null && !meuForm.getDataInicio().isEmpty() ) {
			po.setDataInicio( Utilidades.parseDate( meuForm.getDataInicio() ) );
		} else {
			po.setDataInicio( null );
		}

		if ( meuForm.getDataTermino() != null && !meuForm.getDataTermino().isEmpty() ) {
			po.setDataTermino( Utilidades.parseDate( meuForm.getDataTermino() ) );
		} else {
			po.setDataTermino( null );
		}

		if ( meuForm.getNome() != null && !meuForm.getNome().isEmpty() ) {
			po.setNome( meuForm.getNome() );
		} else {
			po.setNome( null );
		}
		return po;
	}
}
