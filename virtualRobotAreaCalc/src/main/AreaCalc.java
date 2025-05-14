package main;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.Interaction;
import unibo.basicomm23.msg.ProtocolType;
import unibo.basicomm23.utils.ApplAbstractObserver;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.ConnectionFactory;
import unibo.basicomm23.ws.WsConnection;

public class AreaCalc extends ApplAbstractObserver {

	private JSONParser simpleparser = new JSONParser();
	private String addr = "localhost:8091";
	private String service = "api/move";

	private int lefturn = 0;
	private int base = 0;
	private int altezza = 0;

	protected Interaction conn;

	@Override
	public void update(String message) {

		JSONObject jsonObj;
		CommUtils.outmagenta("TestMovesUsingWs | onMessage:" + message);
		try {
			jsonObj = (JSONObject) simpleparser.parse(message);

			if (jsonObj.containsKey("endmove")) {

				if (jsonObj.get("endmove").toString().equals("false")) {

					if (jsonObj.get("move").toString().equals("moveForward-collision")) {

						if (lefturn == 0) {

							doLeftt();

						} else {
							CommUtils.outred("il rettangolo ha altezza pari a: " + altezza + " e base pari a: " + base
									+ ", l'area è: " + base * altezza);
						}

					}

				} else {

					if (jsonObj.get("move").toString().equals("moveForward")) {

						if (lefturn == 0) {

							altezza++;
						} else {
							base++;
						}

						doForward();
					}

					if (jsonObj.get("move").toString().equals("turnLeft")) {

						lefturn++;
						doForward();

					}

				}

			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	private void init() {

		try {
			conn = ConnectionFactory.createClientSupport(ProtocolType.ws, addr, service);
			CommUtils.outblue("AreaCalc |  init conn=" + conn);
			((WsConnection) conn).addObserver(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public AreaCalc() {

		init();

	}

	public void doForward() {

		CommUtils.outblue("AreaCalc | doForward");

		try {
			conn.forward(Messages.forwardcmd);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doLeftt() {

		CommUtils.outblue("AreaCalc | doleft");

		try {
			conn.forward(Messages.turnleftcmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String Args[]) {
		AreaCalc calc = new AreaCalc();
		calc.doForward();

	}

}
