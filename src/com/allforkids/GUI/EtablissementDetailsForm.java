/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.GUI;

import com.allforkids.Entities.Etablissement;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Syrine
 */
public class EtablissementDetailsForm {

    Form etablissementDetailForm;
    String urlForImage = "http://localhost/allforkid/allforkids/web/uploads/images/";
    private Resources theme;
    Form detail;

    public EtablissementDetailsForm(Etablissement e) {
        theme = UIManager.initFirstTheme("/theme");

        detail = new Form("Details Etablissement", BoxLayout.y());

        Toolbar.setGlobalToolbar(true);
        detail.setScrollableY(true);
        detail.setSmoothScrolling(true);
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, s);

        EncodedImage enco = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        URLImage imageserveur = URLImage.createToStorage(enco, urlForImage + e.getImageEtablissement(), urlForImage + e.getImageEtablissement(), URLImage.RESIZE_SCALE_TO_FILL);
        ImageViewer img = new ImageViewer(imageserveur);

        Label lbdes = new Label("Description :");
        SpanLabel tdes = new SpanLabel();
        tdes.setText(e.getDescriptionEtablissement());

        Label lbphone = new Label("Numéro de téléphone :");
        SpanLabel tphone = new SpanLabel();
        tphone.setText(e.getPhoneEtablissement());

        Label lbadr = new Label("Adresse :");
        SpanLabel tadr = new SpanLabel();
        tadr.setText(e.getAdresseEtablissement());

        detail.add(img);
        detail.add(lbdes);
        detail.add(tdes);
        detail.add(lbphone);
        detail.add(tphone);
        detail.add(lbadr);
        detail.add(tadr);
        detail.add(createStarRankSlider());

        detail.getToolbar().addCommandToOverflowMenu("Ajouter aux favoris", icon, ev -> {

        });

    }

    public Form getDetail() {
        return detail;
    }

    public void setDetail(Form detail) {
        this.detail = detail;
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
    Slider starRank = new Slider();
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(10);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    return starRank;
    
 
    }

}
