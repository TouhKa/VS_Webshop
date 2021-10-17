package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.controller.microservices.ProductServiceAction;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.microservices.User;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteProductAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3666796923937616729L;
	private ProductServiceAction productServiceAction = new ProductServiceAction();
	private int id;

	public String execute() throws Exception {
		
		String res = "input";
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("webshop_user");
		
		if(user != null && (user.getRoleId()==2)) {

			productServiceAction.deleteProduct(id);
			{
				res = "success";
			}
		}
		
		return res;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
