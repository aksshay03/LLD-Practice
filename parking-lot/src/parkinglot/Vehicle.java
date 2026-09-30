package parkinglot;

public class Vehicle {
    private String licenseNumber;
    private VehicleType VehicleType;

    public Vehicle(String licenseNumber, VehicleType Vehicletype)
    {
        this.licenseNumber = licenseNumber;
        this.VehicleType = Vehicletype;
    }
    public String getlicenseNumber()
    {
        return licenseNumber;
    }
    public VehicleType getVehicleType()
    {
        return VehicleType;
    }
}