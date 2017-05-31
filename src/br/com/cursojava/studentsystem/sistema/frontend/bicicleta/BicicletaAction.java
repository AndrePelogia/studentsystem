package br.com.cursojava.studentsystem.sistema.frontend.bicicleta;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import br.com.cursojava.studentsystem.exception.ApplicationException;
import br.com.cursojava.studentsystem.sistema.backend.aluno.model.AlunoPO;
import br.com.cursojava.studentsystem.sistema.backend.bicicleta.controller.BicicletaFACADE;
import br.com.cursojava.studentsystem.sistema.backend.bicicleta.model.BicicletaPO;
import br.com.cursojava.studentsystem.utilidades.Messages;
import br.com.cursojava.studentsystem.utilidades.Utilidades;

public class BicicletaAction extends DispatchAction{

	public ActionForward abrirTela( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		BicicletaForm meuForm = (BicicletaForm) form;
		meuForm.limparCampos();

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		//	UTILIZO O FILTRAR PORQUE DEFINIMOS, POIS QUANDO ABRO A TELA E SE JÁ HÁ ALGUM CADASTRO ELE JÁ MOSTRA, OU AO INSERIR UM NOVO ELE JÁ ATUALIZA
		return filtrar( mapping, meuForm, request, response );
	}

	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {

			BicicletaForm meuForm = (BicicletaForm) form;

			BicicletaFACADE facade = new BicicletaFACADE();

			ArrayList< BicicletaPO > encontrados = facade.filtrar( montarPO( meuForm ) );
			meuForm.setBicicletas( encontrados );

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
		return mapping.findForward( "bicicletaView" );
	}

	private BicicletaPO montarPO( BicicletaForm meuForm ) throws ParseException {

		BicicletaPO po = new BicicletaPO();

		if ( meuForm.getId() != null && !meuForm.getId().isEmpty() ) {
			po.setId( Long.valueOf( meuForm.getId() ) );
		} else {
			po.setId( null );
		}

		AlunoPO aluno = new AlunoPO();
		if ( meuForm.getIdAluno() != null && !meuForm.getIdAluno().isEmpty() ) {
			aluno.setId( Long.valueOf( meuForm.getIdAluno() ) );
			;
		} else {
			aluno.setId( null );
		}

		po.setAluno( aluno );

		if ( meuForm.getCor() != null && !meuForm.getCor().isEmpty() ) {
			po.setCor( meuForm.getCor() );
		} else {
			po.setCor( null );
		}

		if ( meuForm.getDataFabricacao() != null && !meuForm.getDataFabricacao().isEmpty() ) {
			po.setDataFabricacao( Utilidades.parseDate( meuForm.getDataFabricacao() ) );
		} else {
			po.setDataFabricacao( null );
		}

		if ( meuForm.getMarca() != null && !meuForm.getMarca().isEmpty() ) {
			po.setMarca( meuForm.getMarca() );
		} else {
			po.setMarca( null );
		}

		if ( meuForm.getMarcha() != null && !meuForm.getMarcha().isEmpty() ) {
			if ( meuForm.getMarcha().equals( "Sim" ) ) {
				po.setMarcha( true );
			} else {
				po.setMarcha( false );
			}
		} else {
			po.setMarcha( null );
		}

		if ( meuForm.getMaterial() != null && !meuForm.getMaterial().isEmpty() ) {
			po.setMaterial( meuForm.getMaterial() );
		} else {
			po.setMaterial( null );
		}

		if ( meuForm.getModalidade() != null && !meuForm.getModalidade().isEmpty() ) {
			po.setModalidade( meuForm.getModalidade() );
		} else {
			po.setModalidade( null );
		}

		if ( meuForm.getPreco() != null && !meuForm.getPreco().isEmpty() ) {
			po.setPreco( Utilidades.parseBigDecimal( meuForm.getPreco() ) );
		} else {
			po.setPreco( null );
		}

		return po;
	}

	public ActionForward inserir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		try {

			BicicletaForm meuForm = (BicicletaForm) form;

			BicicletaFACADE facade = new BicicletaFACADE();

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

			BicicletaForm meuForm = (BicicletaForm) form;

			BicicletaFACADE facade = new BicicletaFACADE();

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

			BicicletaForm meuForm = (BicicletaForm) form;

			BicicletaFACADE facade = new BicicletaFACADE();

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
			BicicletaForm meuForm = (BicicletaForm) form;

			BicicletaFACADE facade = new BicicletaFACADE();

			BicicletaPO encontrado = facade.filtrarPorId( Long.valueOf( meuForm.getId() ) );

			meuForm.setId( encontrado.getId().toString() );
			meuForm.setCor( encontrado.getCor() );
			meuForm.setDataFabricacao( Utilidades.parseDate( encontrado.getDataFabricacao() ) );
			meuForm.setMarca( encontrado.getMarca() );
			if ( encontrado.getMarcha().equals( true ) ) {
				meuForm.setMarcha( "Sim" );
			} else {
				meuForm.setMarcha( "Não" );
			}
			meuForm.setMaterial( encontrado.getMaterial() );
			meuForm.setModalidade( encontrado.getModalidade() );
			meuForm.setPreco( Utilidades.parseBigDecimal( encontrado.getPreco() ) );
			meuForm.setIdAluno( encontrado.getAluno().getId().toString() );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "bicicletaView" );
	}
}
