PGDMP  !    !                |            HotelManagementSystemDatabase    16.1    16.1 "               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16984    HotelManagementSystemDatabase    DATABASE     �   CREATE DATABASE "HotelManagementSystemDatabase" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
 /   DROP DATABASE "HotelManagementSystemDatabase";
                postgres    false            �            1259    16985    hotel    TABLE     �  CREATE TABLE public.hotel (
    hotel_id bigint NOT NULL,
    hotel_name text NOT NULL,
    hotel_address text NOT NULL,
    hotel_mail text NOT NULL,
    hotel_phone text NOT NULL,
    hotel_star text NOT NULL,
    hotel_carpark boolean NOT NULL,
    hotel_wifi boolean NOT NULL,
    hotel_pool boolean NOT NULL,
    hotel_fitness boolean NOT NULL,
    hotel_concierge boolean NOT NULL,
    hotel_spa boolean NOT NULL,
    hotel_roomservice boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    16990    Hotel_hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN hotel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Hotel_hotel_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215            �            1259    16991    pension    TABLE     �   CREATE TABLE public.pension (
    pension_id bigint NOT NULL,
    hotel_id bigint NOT NULL,
    pension_type text NOT NULL,
    pension_factor double precision NOT NULL
);
    DROP TABLE public.pension;
       public         heap    postgres    false            �            1259    16996    pension_pension_id_seq    SEQUENCE     �   ALTER TABLE public.pension ALTER COLUMN pension_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217            �            1259    16997    reservation    TABLE     s  CREATE TABLE public.reservation (
    reservation_id bigint NOT NULL,
    room_id bigint NOT NULL,
    check_in_date date NOT NULL,
    check_out_date date NOT NULL,
    total_price double precision NOT NULL,
    guest_count bigint NOT NULL,
    guest_name text NOT NULL,
    guest_citizen_id text NOT NULL,
    guest_mail text NOT NULL,
    guest_phone text NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    17002    reservation_reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN reservation_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    219            �            1259    17003    room    TABLE     �  CREATE TABLE public.room (
    room_id bigint NOT NULL,
    hotel_name text NOT NULL,
    pension_type text NOT NULL,
    room_type text NOT NULL,
    stock bigint NOT NULL,
    adult_price double precision NOT NULL,
    child_price double precision NOT NULL,
    bed_capacity bigint NOT NULL,
    square_meter text NOT NULL,
    tv boolean NOT NULL,
    minibar boolean NOT NULL,
    konsol boolean NOT NULL,
    kasa boolean NOT NULL,
    projeksiyon boolean NOT NULL,
    hotel_id bigint NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    17008    room_room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN room_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    221            �            1259    17009    season    TABLE     �   CREATE TABLE public.season (
    season_id bigint NOT NULL,
    hotel_id bigint NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL,
    season_factor double precision NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    17012    season_season_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN season_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    223            �            1259    17013    user    TABLE     �   CREATE TABLE public."user" (
    user_id bigint NOT NULL,
    user_name text NOT NULL,
    user_pass text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    17018    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    225                      0    16985    hotel 
   TABLE DATA           �   COPY public.hotel (hotel_id, hotel_name, hotel_address, hotel_mail, hotel_phone, hotel_star, hotel_carpark, hotel_wifi, hotel_pool, hotel_fitness, hotel_concierge, hotel_spa, hotel_roomservice) FROM stdin;
    public          postgres    false    215   �)                 0    16991    pension 
   TABLE DATA           U   COPY public.pension (pension_id, hotel_id, pension_type, pension_factor) FROM stdin;
    public          postgres    false    217   �*                 0    16997    reservation 
   TABLE DATA           �   COPY public.reservation (reservation_id, room_id, check_in_date, check_out_date, total_price, guest_count, guest_name, guest_citizen_id, guest_mail, guest_phone) FROM stdin;
    public          postgres    false    219   �*       
          0    17003    room 
   TABLE DATA           �   COPY public.room (room_id, hotel_name, pension_type, room_type, stock, adult_price, child_price, bed_capacity, square_meter, tv, minibar, konsol, kasa, projeksiyon, hotel_id) FROM stdin;
    public          postgres    false    221   ,                 0    17009    season 
   TABLE DATA           ]   COPY public.season (season_id, hotel_id, start_date, finish_date, season_factor) FROM stdin;
    public          postgres    false    223   �-                 0    17013    user 
   TABLE DATA           J   COPY public."user" (user_id, user_name, user_pass, user_role) FROM stdin;
    public          postgres    false    225   ..                  0    0    Hotel_hotel_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public."Hotel_hotel_id_seq"', 10, true);
          public          postgres    false    216                       0    0    pension_pension_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.pension_pension_id_seq', 22, true);
          public          postgres    false    218                       0    0    reservation_reservation_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.reservation_reservation_id_seq', 22, true);
          public          postgres    false    220                       0    0    room_room_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.room_room_id_seq', 28, true);
          public          postgres    false    222                       0    0    season_season_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.season_season_id_seq', 21, true);
          public          postgres    false    224                       0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 17, true);
          public          postgres    false    226            j           2606    17020    hotel Hotel_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT "Hotel_pkey" PRIMARY KEY (hotel_id);
 <   ALTER TABLE ONLY public.hotel DROP CONSTRAINT "Hotel_pkey";
       public            postgres    false    215            l           2606    17022    pension pension_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pension_id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            postgres    false    217            n           2606    17024    reservation reservation_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    219            p           2606    17026    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    221            r           2606    17028    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    223            t           2606    17030    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    225               �   x�m�A�0E��S�vA(�a�B�[7)Ri)�ོ���.�8�����|
;��T�6�������l�"I3�����*�W��=ep�l���1�4f�0��p��F#��h�G�pF�~u���7K��5�%��O7�~�k�6ʦ ����śe+r		!��R�         `   x�3����))JT�H-R8:?�R�%1#3��Pϔ�ȀӐ�?%Q�;1�,1���F��PܐӒC�������[iN��S~bQ
P�Ј+F��� l�           x���Mj�0�ףS�6�����)���u7�1F�v��.��=C�P�#����FO�c�FX�Q���B�9�,Ȃ'>\��9� 6�S��3�Es�k��\L� Ԃ����E���$���wك���Nn�������ҙ@9�?؏����0�G@n�٫���z[�e�~�z�oZR�����j\Ȩ��_qi�8���S�eD�R�c\r��r�wLB'A�7uL��P�wA�f�VZM9<��&	:_�1x�#�d��ycoc�9z�      
   �  x��S�N�0���}�v+?�b�b �&�|B�j�L錼�^z�3��eG7� 1Ͷ���|�2=|Q��`��
篨��F�̴Ql��p�<nFX�E�jg����?rE�8W�i���H"����]A�!�Sڥ�Ѕ�19�Eu	��5Jt]�efTj�(S.Pl��p��	7�-]¹~N5��U_��"ӚN��*W��+�xg�AX�H;�d.a�:D�T+/-� �.�ޑ���p}y]L�K�|pRL��4��#��ʅ���2xF�"�Ex�/M����ʡQ�?Ѕ�|��V�
E�0�m����+���]�<}���$�����$Q5�R��F3�]0t`�4�~A�V੷;\�6�����6u�h���"D,���,",9}���<L��o���!���	q         R   x�e���0�ްw$M�K���D��/,�3�@����A��Cq���6��^�Ώ��+��Tz���we�X��ퟦ�/�r�         Q   x�3�L�-�ɯLM�4426�t��M9�/�4��4C5�,H,��N�4Nǔ��<.Cs���\�~�g"�F����� |�     