package co.olivemedia.olivemediasampleproject.webservice;

public interface WebResponseConstants {

	interface ResponseCode{

		int OK = 200;

		int CREATED = 201;

		int UN_SUCCESSFULL = 400;

		int UN_AUTHORIZED = 401;
		
		int NO_QUESTIONS = 422;

		int SERVER_ERROR = 500;
		
		int NOT_ENOUGH_COIN = 300;
	}

}
