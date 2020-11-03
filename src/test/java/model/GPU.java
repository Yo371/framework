package model;

import java.util.Objects;

public class GPU {
    private int amount;
    private String gpuType;

    public GPU(int amount, String gpuType) {
        this.amount = amount;
        this.gpuType = gpuType;
    }

    public String getAmount() {
        return String.valueOf(amount);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getGpuType() {
        return gpuType;
    }

    public void setGpuType(String gpuType) {
        this.gpuType = gpuType;
    }

    @Override
    public String toString() {
        return "GPU{" +
                "amount=" + amount +
                ", gpuType='" + gpuType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GPU gpu = (GPU) o;
        return amount == gpu.amount &&
                Objects.equals(gpuType, gpu.gpuType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, gpuType);
    }
}
