/**
 * Copyright 2005-2042, Stadt Bielefeld
 *
 * This file is part of AUIK (Anlagen- und Indirekteinleiter-Kataster).
 *
 * AUIK is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 2 of the License, or (at your
 * option) any later version.
 *
 * AUIK is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with AUIK. If not, see <http://www.gnu.org/licenses/>.
 *
 * AUIK has been developed by Stadt Bielefeld and Intevation GmbH.
 */

package de.bielefeld.umweltamt.aui.utils;

import de.bielefeld.umweltamt.aui.mappings.elka_sync.EAdresse;

/**
 * A little utility class for String operations.
 *
 * @author <a href="mailto:post@connz.de">Conny Pearce (u633z)</a>
 */
public class StringUtils
{
	/**
	 * Create an address String from the given EAdresse instance.
	 * @param address EAdresse instance
	 * @return Address String
	 */
	public static String createAddressString(EAdresse address) {
		return createAdressString(
			address.getStrasse(), address.getHausnr(),
			address.getPlzZst(), address.getOrtZst());
	}
	/**
	 * Create an address String from the given parts.
	 * @param street
	 * @param houseNumber
	 * @param postalCode
	 * @param city
	 * @return Address String
	 */
	public static String createAdressString(
			String street, String houseNumber,
			String postalCode, String city) {
		String ret = String.format("%s %s, %s %s",
			street, houseNumber, postalCode, city);
		return ret;
	}

	/**
	 * Little helper method to set a strike through the text via HTML.<br>
	 * 
	 * @param text
	 * @return String The text with HTML formatting for a strike
	 */
	public static String setStrike(String text)
	{
		return ("<html><strike>" + text + "</strike></html>");
	}

	// ****************************************************************************************************************

	/**
	 * Methode vergleicht die beiden übergebenenen Strings
	 * 
	 * @return
	 */
	public static int compare(String op1, String op2, boolean ignorecase)
	{
		if (op1 == null)
		{
			op1 = ""; 
		}

		if (op2 == null)
		{
			op2 = "";
		}

		if (ignorecase)
		{
			return op1.compareToIgnoreCase(op2);
		}
		else
		{
			return op1.compareTo(op2);
		}
	}

	// ****************************************************************************************************************

	/**
	 * Methode vergleicht die beiden übergebenenen Strings
	 * 
	 * @return
	 */
	public static boolean equals(String op1, String op2, boolean ignorecase)
	{
		if (isNullOrEmpty(op1))
		{
			if (isNullOrEmpty(op2))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			if (isNullOrEmpty(op2))
			{
				return false;
			}
			else
			{
				if (ignorecase)
				{
					return op1.compareToIgnoreCase(op2) == 0;
				}
				else
				{
					return op1.compareTo(op2) == 0;
				}
			}
		}
	}

	// ****************************************************************************************************************

	/**
	 * Methode liefert true, wenn der String leer oder null ist
	 * 
	 * @param txt
	 * @return
	 */
	public static boolean isNullOrEmpty(String txt)
	{
		if (txt == null)
		{
			return true;
		}
		else if (txt.trim().isEmpty())
		{
			return true;
		}

		return false;
	}

}
