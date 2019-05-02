/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Jake
 */

public class GameFrame extends javax.swing.JFrame {

    // Custom variables
    private int choice = -1;
    
    /**
     * Creates new form GameFrame
     */
    public GameFrame() 
    {
        initComponents();
    }
    
    public void setPlayers(int num, Player[] players)
    {
        // Make sure all cards are visible
        resetUI();
        
        // Depending on how many players there are, change visibility
        switch(num)
        {
            case 1:
                Player2Panel.setVisible(false);
                Player2Score.setVisible(false);
            case 2:
                Player3Panel.setVisible(false);
                Player3Score.setVisible(false);
            case 3:
                Player4Panel.setVisible(false);
                Player4Score.setVisible(false);
                break;
        }
    }
    
    public void resetUI()
    {
        Player2Panel.setVisible(true);
        Player3Panel.setVisible(true);
        Player4Panel.setVisible(true);
        
        Player2Score.setVisible(true);
        Player3Score.setVisible(true);
        Player4Score.setVisible(true);
    }
    
    public void init()
    {
        DealerTotal.setText("");
        PlayerTotal.setText("");
        EventText.setText("Start game?");
    }
    
    
    public void initializeCards(Player[] players, Player dealer)
    {   
        // Initalize player and dealer event totals
        DealerTotal.setText("Dealer Total: " + dealer.cardTotal);
        PlayerTotal.setText("Player 1 Total: " + players[0].cardTotal);
        EventText.setText("Player 1 Turn! Hit or stay?");
        
        for(int i = 0; i < players.length; i++)
        {
            ArrayList<Card> hand = players[i].getHand();
            int handSize = hand.size();
            
            for(int j = 0; j < handSize; j++)
            {
                Card card = hand.get(j);
                String desc = new String("Face: " + card.getFaceStr() + "\n"
                                       + "Suit: " + card.getSuitStr() + "\n"
                                       + "Value: " + card.getValue() + "\n");
                JComponent newCard = makeTextPanel(desc);
                
                switch(i)
                {
                    case 0:
                        Player1Cards.addTab("Card " + (j+1), new ImageIcon(), newCard);
                        break;
                    case 1:
                        Player2Cards.addTab("Card " + (j+1), new ImageIcon(), newCard);
                        break;
                    case 2:
                        Player3Cards.addTab("Card " + (j+1), new ImageIcon(), newCard);
                        break;
                    case 3:
                        Player4Cards.addTab("Card " + (j+1), new ImageIcon(), newCard);
                        break;
                }
            }
        }
        
        // Code for dealer hand
        ArrayList<Card> hand = dealer.getHand();
        int handSize = hand.size();

        for(int j = 0; j < handSize; j++)
        {
            Card card = hand.get(j);
            String desc = new String("Face: " + card.getFaceStr() + "\n"
                                   + "Suit: " + card.getSuitStr() + "\n"
                                   + "Value: " + card.getValue() + "\n");
            JComponent newCard = makeTextPanel(desc);

            DealerCards.addTab("Card " + (j+1), new ImageIcon(), newCard);
        }
    }
    
    public void textEvent(String text)
    {
        EventText.setText(text);
    }
    
    public void win(Player p)
    {
        if(p.getPlace() == 0)
        {
            EventText.setText("The Dealer Wins the Game!!!");
        }
        else
        {
            EventText.setText("Player " + p.getPlace() + " Wins the Game!!!");
        }
        DealerTotal.setText("");
        PlayerTotal.setText("");
    }
    
    public void updateScores(Player[] players, Player dealer)
    {
        for(int i = 0; i < players.length; i++)
        {
            switch(i+1)
            {
                case 1:
                    Player1Score.setText("Player 1 Score: " + players[0].getPoints());
                    break;
                case 2:
                    Player2Score.setText("Player 2 Score: " + players[1].getPoints());
                    break;
                case 3:
                    Player3Score.setText("Player 3 Score: " + players[2].getPoints());
                    break;
                case 4:
                    Player4Score.setText("Player 4 Score: " + players[3].getPoints());
                    break;
            }
        }
        
        DealerScore.setText("Dealer Score: " + dealer.getPoints());
    }
    
    
    public void updateTotals(int dealer, int player, int place)
    {
        DealerTotal.setText("Dealer Total: " + dealer);
        PlayerTotal.setText("Player " + place + " Total: " + player);
    }
    
    public void addCard(Player p, ArrayList<Card> hand)
    {
        Card card = hand.get(hand.size()-1);
        String desc = new String("Face: " + card.getFaceStr() + "\n"
                               + "Suit: " + card.getSuitStr() + "\n"
                               + "Value: " + card.getValue() + "\n");
        JComponent newCard = makeTextPanel(desc);
        
        
        // Switch statement to determine who gets the card
        switch(p.getPlace())
        {
            // Dealer Case
            case 0:
                DealerCards.addTab("Card " + hand.size(), new ImageIcon(), newCard);
                break;
            case 1:
                Player1Cards.addTab("Card " + hand.size(), new ImageIcon(), newCard);
                break;
            case 2:
                Player2Cards.addTab("Card " + hand.size(), new ImageIcon(), newCard);
                break;
            case 3:
                Player3Cards.addTab("Card " + hand.size(), new ImageIcon(), newCard);
                break;
            case 4:
                Player4Cards.addTab("Card " + hand.size(), new ImageIcon(), newCard);
                break;
        }
        
        checkConv(p);
    }
    
    private void checkConv(Player p)
    {
        int index = p.convCard;
        
        if(index != -1)
        {
            ArrayList<Card> hand = p.getHand();
            Card card = hand.get(index);
            String desc = new String("Face: " + card.getFaceStr() + "\n"
                                   + "Suit: " + card.getSuitStr() + "\n"
                                   + "Value: " + card.getValue() + "\n");
            JComponent fixCard = makeTextPanel(desc);
            
            // Reset tab
            switch(p.getPlace())
            {
            // Dealer Case
            case 0:
                DealerCards.removeTabAt(index);
                DealerCards.insertTab("Card " + (index + 1), new ImageIcon(), fixCard, "", index);
                break;
            case 1:
                Player1Cards.removeTabAt(index);
                Player1Cards.insertTab("Card " + (index + 1), new ImageIcon(), fixCard, "", index);
                break;
            case 2:
                Player2Cards.removeTabAt(index);
                Player2Cards.insertTab("Card " + (index + 1), new ImageIcon(), fixCard, "", index);
                break;
            case 3:
                Player3Cards.removeTabAt(index);
                Player3Cards.insertTab("Card " + (index + 1), new ImageIcon(), fixCard, "", index);
                break;
            case 4:
                Player4Cards.removeTabAt(index);
                Player4Cards.insertTab("Card " + (index + 1), new ImageIcon(), fixCard, "", index);
                break;
            }    
            
            // Converted card taken care of, set back to -1
            p.convCard = -1;
        }
        
        
    }
    
    public void clearHands()
    {
        Player1Cards.removeAll();
        Player2Cards.removeAll();
        Player3Cards.removeAll();
        Player4Cards.removeAll();
        DealerCards.removeAll();
    }
    
    
    public int listen()
    {
        // Ideally infinite loop
        while(choice != 0 && choice != 1) {}
        
        return choice;
    }
    
    
    protected JComponent makeTextPanel(String text) 
    {
        JPanel panel = new JPanel(false);
        JTextArea filler = new JTextArea(text);
        // filler.setHorizontalAlignment(JLabel.CENTER);
        filler.setEditable(false);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Playspace = new javax.swing.JPanel();
        DealerTotal = new javax.swing.JLabel();
        PlayerTotal = new javax.swing.JLabel();
        EventText = new javax.swing.JLabel();
        Player1Panel = new javax.swing.JLayeredPane();
        Player1Text = new javax.swing.JLabel();
        Player1Cards = new javax.swing.JTabbedPane();
        Player2Panel = new javax.swing.JLayeredPane();
        Player2Text = new javax.swing.JLabel();
        Player2Cards = new javax.swing.JTabbedPane();
        Player3Panel = new javax.swing.JLayeredPane();
        Player3Text = new javax.swing.JLabel();
        Player3Cards = new javax.swing.JTabbedPane();
        Player4Panel = new javax.swing.JLayeredPane();
        Player4Text = new javax.swing.JLabel();
        Player4Cards = new javax.swing.JTabbedPane();
        Draw = new javax.swing.JButton();
        Stay = new javax.swing.JButton();
        DiscardPanel = new javax.swing.JLayeredPane();
        DiscardText = new javax.swing.JLabel();
        DealerPanel = new javax.swing.JLayeredPane();
        DealerText = new javax.swing.JLabel();
        DealerCards = new javax.swing.JTabbedPane();
        DealerScore = new javax.swing.JLabel();
        Player1Score = new javax.swing.JLabel();
        Player2Score = new javax.swing.JLabel();
        Player3Score = new javax.swing.JLabel();
        Player4Score = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Blackjack");
        setBackground(new java.awt.Color(255, 255, 255));

        Playspace.setBackground(new java.awt.Color(255, 255, 255));
        Playspace.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Playspace.setToolTipText("Playspace");
        Playspace.setName("Playspace"); // NOI18N

        DealerTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        DealerTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DealerTotal.setText("Dealer Total:");

        PlayerTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PlayerTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlayerTotal.setText("Player Total:");

        EventText.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        EventText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EventText.setText("Player 1 Turn!");

        javax.swing.GroupLayout PlayspaceLayout = new javax.swing.GroupLayout(Playspace);
        Playspace.setLayout(PlayspaceLayout);
        PlayspaceLayout.setHorizontalGroup(
            PlayspaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlayspaceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PlayspaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DealerTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PlayerTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                    .addComponent(EventText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PlayspaceLayout.setVerticalGroup(
            PlayspaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlayspaceLayout.createSequentialGroup()
                .addComponent(DealerTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EventText, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PlayerTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Player1Panel.setBackground(new java.awt.Color(102, 102, 255));
        Player1Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Player1Panel.setOpaque(true);

        Player1Text.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Player1Text.setForeground(new java.awt.Color(255, 255, 255));
        Player1Text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player1Text.setLabelFor(DiscardPanel);
        Player1Text.setText("Player 1 Deck");

        Player1Panel.setLayer(Player1Text, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Player1Panel.setLayer(Player1Cards, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout Player1PanelLayout = new javax.swing.GroupLayout(Player1Panel);
        Player1Panel.setLayout(Player1PanelLayout);
        Player1PanelLayout.setHorizontalGroup(
            Player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Player1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Player1Text)
                .addContainerGap(18, Short.MAX_VALUE))
            .addComponent(Player1Cards, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        Player1PanelLayout.setVerticalGroup(
            Player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Player1PanelLayout.createSequentialGroup()
                .addComponent(Player1Text)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Player1Cards, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
        );

        Player2Panel.setBackground(new java.awt.Color(51, 255, 51));
        Player2Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Player2Panel.setOpaque(true);

        Player2Text.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Player2Text.setForeground(new java.awt.Color(255, 255, 255));
        Player2Text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player2Text.setLabelFor(DiscardPanel);
        Player2Text.setText("Player 2 Deck");

        Player2Panel.setLayer(Player2Text, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Player2Panel.setLayer(Player2Cards, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout Player2PanelLayout = new javax.swing.GroupLayout(Player2Panel);
        Player2Panel.setLayout(Player2PanelLayout);
        Player2PanelLayout.setHorizontalGroup(
            Player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Player2PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Player2Text)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(Player2Cards)
        );
        Player2PanelLayout.setVerticalGroup(
            Player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Player2PanelLayout.createSequentialGroup()
                .addComponent(Player2Text)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Player2Cards))
        );

        Player3Panel.setBackground(new java.awt.Color(255, 51, 51));
        Player3Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Player3Panel.setOpaque(true);

        Player3Text.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Player3Text.setForeground(new java.awt.Color(255, 255, 255));
        Player3Text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player3Text.setLabelFor(DiscardPanel);
        Player3Text.setText("Player 3 Deck");

        Player3Panel.setLayer(Player3Text, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Player3Panel.setLayer(Player3Cards, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout Player3PanelLayout = new javax.swing.GroupLayout(Player3Panel);
        Player3Panel.setLayout(Player3PanelLayout);
        Player3PanelLayout.setHorizontalGroup(
            Player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Player3PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Player3Text)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(Player3Cards)
        );
        Player3PanelLayout.setVerticalGroup(
            Player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Player3PanelLayout.createSequentialGroup()
                .addComponent(Player3Text)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Player3Cards, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
        );

        Player4Panel.setBackground(new java.awt.Color(204, 51, 255));
        Player4Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Player4Panel.setOpaque(true);

        Player4Text.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Player4Text.setForeground(new java.awt.Color(255, 255, 255));
        Player4Text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Player4Text.setLabelFor(DiscardPanel);
        Player4Text.setText("Player 4 Deck");

        Player4Panel.setLayer(Player4Text, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Player4Panel.setLayer(Player4Cards, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout Player4PanelLayout = new javax.swing.GroupLayout(Player4Panel);
        Player4Panel.setLayout(Player4PanelLayout);
        Player4PanelLayout.setHorizontalGroup(
            Player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Player4PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Player4Text)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(Player4Cards)
        );
        Player4PanelLayout.setVerticalGroup(
            Player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Player4PanelLayout.createSequentialGroup()
                .addComponent(Player4Text)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Player4Cards, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
        );

        Draw.setBackground(new java.awt.Color(51, 255, 51));
        Draw.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Draw.setText("Hit");
        Draw.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Draw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DrawActionPerformed(evt);
            }
        });

        Stay.setBackground(new java.awt.Color(255, 0, 0));
        Stay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Stay.setText("Stay");
        Stay.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Stay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StayActionPerformed(evt);
            }
        });

        DiscardPanel.setBackground(new java.awt.Color(102, 102, 102));
        DiscardPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        DiscardPanel.setOpaque(true);

        DiscardText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        DiscardText.setForeground(new java.awt.Color(255, 255, 255));
        DiscardText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DiscardText.setLabelFor(DiscardPanel);
        DiscardText.setText("Discard Deck");

        DiscardPanel.setLayer(DiscardText, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout DiscardPanelLayout = new javax.swing.GroupLayout(DiscardPanel);
        DiscardPanel.setLayout(DiscardPanelLayout);
        DiscardPanelLayout.setHorizontalGroup(
            DiscardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DiscardPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DiscardText)
                .addContainerGap())
        );
        DiscardPanelLayout.setVerticalGroup(
            DiscardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DiscardPanelLayout.createSequentialGroup()
                .addComponent(DiscardText)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        DealerPanel.setBackground(new java.awt.Color(102, 102, 102));
        DealerPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        DealerPanel.setOpaque(true);

        DealerText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        DealerText.setForeground(new java.awt.Color(255, 255, 255));
        DealerText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DealerText.setLabelFor(DiscardPanel);
        DealerText.setText(" Dealer Deck");

        DealerPanel.setLayer(DealerText, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DealerPanel.setLayer(DealerCards, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout DealerPanelLayout = new javax.swing.GroupLayout(DealerPanel);
        DealerPanel.setLayout(DealerPanelLayout);
        DealerPanelLayout.setHorizontalGroup(
            DealerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DealerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DealerText)
                .addContainerGap(18, Short.MAX_VALUE))
            .addComponent(DealerCards)
        );
        DealerPanelLayout.setVerticalGroup(
            DealerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DealerPanelLayout.createSequentialGroup()
                .addComponent(DealerText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DealerCards, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
        );

        DealerScore.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        DealerScore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        DealerScore.setText("Dealer Score:");
        DealerScore.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Player1Score.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Player1Score.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Player1Score.setText("Player 1 Score:");
        Player1Score.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Player2Score.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Player2Score.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Player2Score.setText("Player 2 Score:");
        Player2Score.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Player3Score.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Player3Score.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Player3Score.setText("Player 3 Score:");
        Player3Score.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Player4Score.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Player4Score.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Player4Score.setText("Player 4 Score:");
        Player4Score.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(Player1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Player2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Player3Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Player4Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(Playspace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Draw, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Stay, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DealerScore, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(DealerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DiscardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Player1Score, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Player2Score, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Player3Score, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Player4Score, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Draw, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Stay, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(DealerPanel)
                                .addComponent(DiscardPanel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(DealerScore, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Player1Score, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Player2Score, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Player3Score, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Player4Score, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(Playspace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Player4Panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Player3Panel)
                            .addComponent(Player2Panel)))
                    .addComponent(Player1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        Playspace.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DrawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DrawActionPerformed
        // TODO add your handling code here:
        choice = 0;
    }//GEN-LAST:event_DrawActionPerformed

    private void StayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StayActionPerformed
        // TODO add your handling code here:
        choice = 1;
    }//GEN-LAST:event_StayActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane DealerCards;
    private javax.swing.JLayeredPane DealerPanel;
    private javax.swing.JLabel DealerScore;
    private javax.swing.JLabel DealerText;
    private javax.swing.JLabel DealerTotal;
    private javax.swing.JLayeredPane DiscardPanel;
    private javax.swing.JLabel DiscardText;
    private javax.swing.JButton Draw;
    private javax.swing.JLabel EventText;
    private javax.swing.JTabbedPane Player1Cards;
    private javax.swing.JLayeredPane Player1Panel;
    private javax.swing.JLabel Player1Score;
    private javax.swing.JLabel Player1Text;
    private javax.swing.JTabbedPane Player2Cards;
    private javax.swing.JLayeredPane Player2Panel;
    private javax.swing.JLabel Player2Score;
    private javax.swing.JLabel Player2Text;
    private javax.swing.JTabbedPane Player3Cards;
    private javax.swing.JLayeredPane Player3Panel;
    private javax.swing.JLabel Player3Score;
    private javax.swing.JLabel Player3Text;
    private javax.swing.JTabbedPane Player4Cards;
    private javax.swing.JLayeredPane Player4Panel;
    private javax.swing.JLabel Player4Score;
    private javax.swing.JLabel Player4Text;
    private javax.swing.JLabel PlayerTotal;
    private javax.swing.JPanel Playspace;
    private javax.swing.JButton Stay;
    // End of variables declaration//GEN-END:variables
}
