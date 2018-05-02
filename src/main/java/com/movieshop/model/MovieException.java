package com.movieshop.model;

public class MovieException extends Exception {

	private static final long serialVersionUID = -7896493305866819829L;

	public MovieException() {
		super();
	}

	public MovieException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public MovieException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MovieException(String arg0) {
		super(arg0);
	}

	public MovieException(Throwable arg0) {
		super(arg0);
	}

	
}
