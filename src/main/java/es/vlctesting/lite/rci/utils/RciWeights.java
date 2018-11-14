package es.vlctesting.lite.rci.utils;

public final class RciWeights {
	
	private final int blocker;
	private final int critical;
	private final int major;
	private final int minor;
	private final int info;
	
    public RciWeights(final int blocker, final int critical, final int major, final int minor, final int info) {
        this.blocker = blocker;
        this.critical = critical;
        this.major = major;
        this.minor = minor;
        this.info = info;
    }

	public int getBlocker() {
		return this.blocker;
	}
	
	public int getCritical() {
		return this.critical;
	}
	
	public int getMajor() {
		return this.major;
	}
	
	public int getMinor() {
		return this.minor;
	}
	
	public int getInfo() {
		return this.info;
	}

}
