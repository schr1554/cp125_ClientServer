package com.scg.util;

import java.io.Serializable;

public enum StateCode implements Serializable {
	AK("ALASKA"), AL("ALABAMA"), AR("ARKANSAS"), AS("AMERICAN SAMOA"), AZ("ARIZONA"), CA("CALIFORNIA"), CO(
			"COLORADO"), CT("CONNECTICUT"), DC("DISTRICT OF COLUMBIA"), DE("DELAWARE"), FL("FLORIDA"), FM(
					"FEDERATED STATES OF MICRONESIA"), GA("GEORGIA"), GU("GUAM"), HI("HAWAII"), IA("IOWA"), ID(
							"IDAHO"), IL("ILLINOIS"), IN("INDIANA"), KS("KANSAS"), KY(
									"KENTUCKY"), LA("LOUISIANA"), MA("MASSACHUSETTS"), MD("MARYLAND"), ME("MAINE"), MH(
											"MARSHLL ISLANDS"), MI("MICHIGAN"), MN("MINNESOTA"), MO("MISSOURI"), MP(
													"NOTHERN MARIANA ISLANDS"), MS("MISSISSIPPI"), MT("MONTANA"), NC(
															"NORTH CAROLINA"), ND("NORTH DAKOTA"), NE(
																	"NEBRASKA"), NH("NEW HAMPSHIRE"), NJ(
																			"NEW JERSEY"), NM("NEW MEXICO"), NV(
																					"NEVADA"), NY("NEW YORK"), OH(
																							"OHIO"), OK("OKLAHOMA"), OR(
																									"OREGON"), PA(
																											"PENNSYLVANIA"), PR(
																													"PUERTO RICO"), PW(
																															"PALAU"), RI(
																																	"ROAD ISLAND"), SC(
																																			"SOUTH CAROLINA"), SD(
																																					"SOUTH DAKOTA"), TN(
																																							"TENNESSEE"), TX(
																																									"TEXAS"), UT(
																																											"UTAH"), VA(
																																													"VIRGINA"), VI(
																																															"VIRGIN ISLANDS"), VT(
																																																	"VERMONT"), WA(
																																																			"WASHINGTON"), WI(
																																																					"WISCONSIN"), WV(
																																																							"WEST VIRGINA"), WY(
																																																									"WYOMING");
	;

	private final String friendlyName;

	private StateCode(final String name) {
		this.friendlyName = name;
	}

	public String toString() {
		return this.friendlyName;

	}

}
