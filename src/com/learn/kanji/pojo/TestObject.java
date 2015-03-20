/**
 * 
 */
package com.learn.kanji.pojo;

/**
 * @author ngocha
 *
 */
public class TestObject {

	private String fourLine;
	private String thirdLine;
	private String secondLine;
	private String firstLine;

	/**
	 * @return the fourLine
	 */
	public String getFourLine() {
		return fourLine;
	}

	/**
	 * @param fourLine
	 *            the fourLine to set
	 */
	public void setFourLine(String fourLine) {
		this.fourLine = fourLine;
	}

	/**
	 * @return the thirdLine
	 */
	public String getThirdLine() {
		return thirdLine;
	}

	/**
	 * @param thirdLine
	 *            the thirdLine to set
	 */
	public void setThirdLine(String thirdLine) {
		this.thirdLine = thirdLine;
	}

	/**
	 * @return the secondLine
	 */
	public String getSecondLine() {
		return secondLine;
	}

	/**
	 * @param secondLine
	 *            the secondLine to set
	 */
	public void setSecondLine(String secondLine) {
		this.secondLine = secondLine;
	}

	/**
	 * @return the firstLine
	 */
	public String getFirstLine() {
		return firstLine;
	}

	/**
	 * @param firstLine
	 *            the firstLine to set
	 */
	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
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
		result = prime * result
				+ ((firstLine == null) ? 0 : firstLine.hashCode());
		result = prime * result
				+ ((fourLine == null) ? 0 : fourLine.hashCode());
		result = prime * result
				+ ((secondLine == null) ? 0 : secondLine.hashCode());
		result = prime * result
				+ ((thirdLine == null) ? 0 : thirdLine.hashCode());
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
		TestObject other = (TestObject) obj;
		if (firstLine == null) {
			if (other.firstLine != null)
				return false;
		} else if (!firstLine.equals(other.firstLine))
			return false;
		if (fourLine == null) {
			if (other.fourLine != null)
				return false;
		} else if (!fourLine.equals(other.fourLine))
			return false;
		if (secondLine == null) {
			if (other.secondLine != null)
				return false;
		} else if (!secondLine.equals(other.secondLine))
			return false;
		if (thirdLine == null) {
			if (other.thirdLine != null)
				return false;
		} else if (!thirdLine.equals(other.thirdLine))
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
		return "TestObject [fourLine=" + fourLine + ", thirdLine=" + thirdLine
				+ ", secondLine=" + secondLine + ", firstLine=" + firstLine
				+ "]";
	}

}
