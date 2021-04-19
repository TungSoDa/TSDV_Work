package com.example.demo.util;

public class MessageResource {
	
	/**
	 * For action
	 */
	public static final String GET_SUCCESS = "GET_SUCCESS";
	public static final String GET_NO_DATA = "GET_NO_DATA";
	public static final String GET_FAIL = "GET_FAIL";
	public static final String INSERT_SUCCESS = "INSERT_SUCCESS";
	public static final String INSERT_FAIL = "INSERT_FAIL";
	public static final String UPDATE_SUCCESS = "UPDATE_SUCCESS";
	public static final String UPDATE_FAIL = "UPDATE_FAIL";
	public static final String DELETE_SUCCESS = "DELETE_SUCCESS";
	public static final String DELETE_FAIL = "DELETE_FAIL";
	public static final String CRUD = "CRUD";
	
	/**
	 * For exception
	 */
	public static final String IO_EXCEPTION = "IO_EXCEPTION";
	public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
	public static final String DATA_ACCESS_EXCEPTION = "DATA_ACCESS_EXCEPTION";
	public static final String MANIPULATE_DB = "MANIPULATE_DB";
	
	
	/**
	 * For API
	 */
	public static final String ADMIN_USERAPI = "/admin/userAPI";
	
	/**
	 * For Service
	 */
	public static final String USER_SERVICE = "USER_SERVICE";
	
	private MessageResource() {
	}

	public static String toMessage(String function, String action) {
		StringBuilder builder = new StringBuilder();
		builder.append(function);
		builder.append(": ");
		builder.append(action);
		return builder.toString();
	}
}
