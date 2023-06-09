    package com.emretaskin.itg.entity;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Data
    @Entity
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "order_items")
    public class OrderItem {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "order_id", nullable = false)
        private Order order;

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "product_id", nullable = false)
        private Product product;

        @Column(nullable = false)
        private int quantity;

        public OrderItem(Order order, Product product, int quantity) {
            this.order = order;
            this.product = product;
            this.quantity = quantity;
        }
    }

