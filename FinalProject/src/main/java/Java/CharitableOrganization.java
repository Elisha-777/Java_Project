package Java;

public class CharitableOrganization {
    private int organizationId;
    private int userId;

    // Constructor
    public CharitableOrganization(int organizationId, int userId) {
        this.organizationId = organizationId;
        this.userId = userId;
    }

    // Getters and Setters
    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
