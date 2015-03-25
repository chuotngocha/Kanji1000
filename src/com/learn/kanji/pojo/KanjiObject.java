/**
 * 
 */
package com.learn.kanji.pojo;

import java.io.Serializable;

/**
 * Object Kanji.
 * 
 * @author ngocha.
 *
 */
public class KanjiObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5454119003874510095L;

	// kanji's id.
	private int id;

	// kanji's code.
	private String code;

	// kanji's onyomi.
	private String onyomi;

	// kanji's kunyomi.
	private String kunyomi;

	// kanji's meaning.
	private String meaning;

	// kanji's type (N1, N2, N3, N4, N5).
	private int type;

	private int isbookmark;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the onyomi
	 */
	public String getOnyomi() {
		return onyomi;
	}

	/**
	 * @param onyomi
	 *            the onyomi to set
	 */
	public void setOnyomi(String onyomi) {
		this.onyomi = onyomi;
	}

	/**
	 * @return the kunyomi
	 */
	public String getKunyomi() {
		return kunyomi;
	}

	/**
	 * @param kunyomi
	 *            the kunyomi to set
	 */
	public void setKunyomi(String kunyomi) {
		this.kunyomi = kunyomi;
	}

	/**
	 * @return the meaning
	 */
	public String getMeaning() {
		return meaning;
	}

	/**
	 * @param meaning
	 *            the meaning to set
	 */
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the isbookmark
	 */
	public int getIsbookmark() {
		return isbookmark;
	}

	/**
	 * @param isbookmark
	 *            the isbookmark to set
	 */
	public void setIsbookmark(int isbookmark) {
		this.isbookmark = isbookmark;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + id;
		result = prime * result + isbookmark;
		result = prime * result + ((kunyomi == null) ? 0 : kunyomi.hashCode());
		result = prime * result + ((meaning == null) ? 0 : meaning.hashCode());
		result = prime * result + ((onyomi == null) ? 0 : onyomi.hashCode());
		result = prime * result + type;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KanjiObject other = (KanjiObject) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id != other.id)
			return false;
		if (isbookmark != other.isbookmark)
			return false;
		if (kunyomi == null) {
			if (other.kunyomi != null)
				return false;
		} else if (!kunyomi.equals(other.kunyomi))
			return false;
		if (meaning == null) {
			if (other.meaning != null)
				return false;
		} else if (!meaning.equals(other.meaning))
			return false;
		if (onyomi == null) {
			if (other.onyomi != null)
				return false;
		} else if (!onyomi.equals(other.onyomi))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KanjiObject [id=" + id + ", code=" + code + ", onyomi="
				+ onyomi + ", kunyomi=" + kunyomi + ", meaning=" + meaning
				+ ", type=" + type + ", isbookmark=" + isbookmark + "]";
	}

}
