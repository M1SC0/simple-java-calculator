package util;

import java.awt.*;

public class CustomGbc extends GridBagConstraints {

    public CustomGbc(Builder builder) {
        this.gridx = builder.gridx;
        this.gridy = builder.gridy;
        this.gridwidth = builder.gridwidth;
        this.gridheight = builder.gridheight;
        this.weightx = builder.weightx;
        this.weighty = builder.weighty;
        this.anchor = builder.anchor;
        this.fill = builder.fill;
        this.insets = builder.insets;
        this.ipadx = builder.ipadx;
        this.ipady = builder.ipady;
    }

    public static class Builder {

        int gridx = 0;
        int gridy = 0;
        int gridwidth = 1;
        int gridheight = 1;
        double weightx = 0;
        double weighty = 0;
        int anchor = GridBagConstraints.CENTER;
        int fill = GridBagConstraints.NONE;
        Insets insets = new Insets(0, 0, 0, 0);
        int ipadx = 0;
        int ipady = 0;

        public Builder setGridx(int gridx) {
            this.gridx = gridx;
            return this;
        }

        public Builder setGridy(int gridy) {
            this.gridy = gridy;
            return this;
        }

        public Builder setGridwidth(int gridwidth) {
            this.gridwidth = gridwidth;
            return this;
        }

        public Builder setGridheight(int gridheight) {
            this.gridheight = gridheight;
            return this;
        }

        public Builder setWeightx(double weightx) {
            this.weightx = weightx;
            return this;
        }

        public Builder setWeighty(double weighty) {
            this.weighty = weighty;
            return this;
        }

        public Builder setAnchor(int anchor) {
            this.anchor = anchor;
            return this;
        }

        public Builder setFill(int fill) {
            this.fill = fill;
            return this;
        }

        public Builder setInsets(Insets insets) {
            this.insets = insets;
            return this;
        }

        public CustomGbc build() {

            return new CustomGbc(this);
        }
    }
}
