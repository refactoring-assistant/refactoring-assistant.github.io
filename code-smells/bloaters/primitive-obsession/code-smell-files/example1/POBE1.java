class ShippingVolumeCheck {
    private int boxHeight;
    private int boxWidth;
    private int boxDepth;
    private int boxVolume;
    final private int ITEMVOLUME;

    private int calculateBoxVolume() {
        int volume = this.boxHeight * this.boxDepth * this.boxWidth;
        return volume;
    }

    public boolean checkItemVolume() {
        return this.boxVolume > this.ITEMVOLUME;
    }

    ShippingVolumeCheck(int boxHeight, int boxDepth, int boxWidth) {
        this.ITEMVOLUME = 12;
        this.boxHeight = boxHeight;
        this.boxDepth = boxDepth;
        this.boxWidth = boxWidth;
        this.boxVolume = calculateBoxVolume();
    }

}

public class POBE1 {
    public static void main(String[] args) {
        ShippingVolumeCheck svc = new ShippingVolumeCheck(10, 12, 10);
        System.out.println(svc.checkItemVolume());
    }

}
