# Weđding Party Manager

+ **Content:** This app allows users to manage weddings they have booked. Users can keep track of event details, such as venue, date, and services, ensuring smooth planning and organization of their weddings.

+ **Development Period:** 1 month


<div align="center">
  <img width="452" alt="image" src="https://github.com/user-attachments/assets/22c75e00-6777-4bb7-94f0-ecdaa6bbe3e4">


</div>


## Build Notes

The project is developed using NetBeans, and you can use this IDE to run the source code efficiently.

## Detail
+ **Framework:** Java Spring.
+ **Database:** MySQL.
+ **Program Java:** [Here is the link to download](https://drive.google.com/drive/folders/1aXSkzzLjVq2_eMAQ58frQ-S5mItJb9Hf).

## Entity relationship diagram.

<div align="center">
<img width="452" alt="image" src="https://github.com/user-attachments/assets/c4fa65e9-e863-457d-af6f-913ff5f111a8">

</div>

## Feature

+ **Wedding Management:** Book weddings, make wedding payments via VNPAY, cancel weddings, search for wedding details, and print invoices.

<div align="center">
<img width="452" alt="image" src="https://github.com/user-attachments/assets/d61ab6d4-8d83-493a-a183-3482066f9aeb">
<img width="452" alt="image" src="https://github.com/user-attachments/assets/da772066-4dc8-42fd-b253-f5a3ec54b7c6">
<img width="229" alt="image" src="https://github.com/user-attachments/assets/9447733f-1e77-4649-9d34-28d586af0556">
<img width="452" alt="image" src="https://github.com/user-attachments/assets/b1eae59b-4149-4d52-8340-90be0263fc27">

</div>


+ **Employee Management:** Assign tasks to employees.

<div align="center">
<img width="452" alt="image" src="https://github.com/user-attachments/assets/e454a401-e339-42d5-8f66-173ceb4b35c1">
</div>

**Revenue Statistics:** The project uses Trigger SQL to automatically generate statistics for wedding invoices that have been paid.

```sql
CREATE TRIGGER `Create_trigger_Insert` 
AFTER INSERT ON `HoaDon` 
FOR EACH ROW 
BEGIN
    -- Kiểm tra điều kiện và chèn vào baocaodoanhthu nếu đúng
    IF (SELECT COUNT(*) 
        FROM hoadon 
        WHERE YEAR((SELECT phieudattieccuoi.ngayDaiTiec 
                    FROM phieudattieccuoi 
                    WHERE hoadon.maTiecCuoi = phieudattieccuoi.maTiecCuoi)) 
            = YEAR((SELECT phieudattieccuoi.ngayDaiTiec 
                    FROM phieudattieccuoi 
                    WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi)) 
        AND MONTH((SELECT phieudattieccuoi.ngayDaiTiec 
                    FROM phieudattieccuoi 
                    WHERE hoadon.maTiecCuoi = phieudattieccuoi.maTiecCuoi)) 
            = MONTH((SELECT phieudattieccuoi.ngayDaiTiec 
                    FROM phieudattieccuoi 
                    WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi))) = 1 THEN

        INSERT INTO baocaodoanhthu (MaBaoCao, nam, thang, tongDoanhThu)
        VALUES (
            CONCAT('BC', LPAD((SELECT COUNT(*) + 1 
                               FROM baocaodoanhthu as bc), 4, '0')),
            YEAR((SELECT phieudattieccuoi.ngayDaiTiec 
                  FROM phieudattieccuoi 
                  WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi)),
            MONTH((SELECT phieudattieccuoi.ngayDaiTiec 
                   FROM phieudattieccuoi 
                   WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi)),
            (SELECT SUM(hoadon.tongTienHoaDon) 
             FROM hoadon 
             WHERE YEAR((SELECT phieudattieccuoi.ngayDaiTiec 
                         FROM phieudattieccuoi 
                         WHERE hoadon.maTiecCuoi = phieudattieccuoi.maTiecCuoi)) 
                 = YEAR((SELECT phieudattieccuoi.ngayDaiTiec 
                         FROM phieudattieccuoi 
                         WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi)) 
             AND MONTH((SELECT phieudattieccuoi.ngayDaiTiec 
                         FROM phieudattieccuoi 
                         WHERE hoadon.maTiecCuoi = phieudattieccuoi.maTiecCuoi)) 
                 = MONTH((SELECT phieudattieccuoi.ngayDaiTiec 
                         FROM phieudattieccuoi 
                         WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi)))
        );

        -- Kiểm tra điều kiện để chèn vào ChiTietBaoCao nếu đúng
        IF (SELECT COUNT(*) 
            FROM hoadon 
            WHERE (SELECT phieudattieccuoi.ngayDaiTiec 
                   FROM phieudattieccuoi 
                   WHERE hoadon.maTiecCuoi = phieudattieccuoi.maTiecCuoi) 
                = (SELECT phieudattieccuoi.ngayDaiTiec 
                   FROM phieudattieccuoi 
                   WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi)) = 1 THEN

            INSERT INTO ChiTietBaoCao (maBaoCao, ngay, soLuongTiec, doanhThu, tiLe)
            VALUES (
                (SELECT bcdt.maBaoCao 
                 FROM baocaodoanhthu as bcdt 
                 WHERE bcdt.thang = MONTH((SELECT phieudattieccuoi.ngayDaiTiec 
                                           FROM phieudattieccuoi 
                                           WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi)) 
                 AND bcdt.nam = YEAR((SELECT phieudattieccuoi.ngayDaiTiec 
                                      FROM phieudattieccuoi 
                                      WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi))),
                (SELECT phieudattieccuoi.ngayDaiTiec 
                 FROM phieudattieccuoi 
                 WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi),
                1,
                NEW.tongTienHoaDon,
                1
            );

            UPDATE chitietbaocao as ctbc0 
            SET ctbc0.tiLe = ctbc0.doanhThu / 
                (SELECT SUM(ctbc.doanhThu) 
                 FROM chitietbaocao as ctbc 
                 WHERE YEAR(ctbc.ngay) = YEAR(ctbc0.ngay) 
                   AND MONTH(ctbc.ngay) = MONTH(ctbc0.ngay)) 
            WHERE YEAR(ctbc0.ngay) = YEAR((SELECT phieudattieccuoi.ngayDaiTiec 
                                           FROM phieudattieccuoi 
                                           WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi)) 
              AND MONTH(ctbc0.ngay) = MONTH((SELECT phieudattieccuoi.ngayDaiTiec 
                                              FROM phieudattieccuoi 
                                              WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi));
        ELSE
            UPDATE chitietbaocao as ctbc0 
            SET ctbc0.soLuongTiec = 
                (SELECT COUNT(*) 
                 FROM hoadon 
                 WHERE (SELECT phieudattieccuoi.ngayDaiTiec 
                        FROM phieudattieccuoi 
                        WHERE hoadon.maTiecCuoi = phieudattieccuoi.maTiecCuoi) 
                   = (SELECT phieudattieccuoi.ngayDaiTiec 
                      FROM phieudattieccuoi 
                      WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi)),
                ctbc0.doanhThu = ctbc0.doanhThu + NEW.tongTienHoaDon 
            WHERE ctbc0.ngay = (SELECT phieudattieccuoi.ngayDaiTiec 
                                FROM phieudattieccuoi 
                                WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi);

            UPDATE chitietbaocao as ctbc0 
            SET ctbc0.tiLe = ctbc0.doanhThu / 
                (SELECT SUM(ctbc.doanhThu) 
                 FROM chitietbaocao as ctbc 
                 WHERE YEAR(ctbc.ngay) = YEAR(ctbc0.ngay) 
                   AND MONTH(ctbc.ngay) = MONTH(ctbc0.ngay)) 
            WHERE YEAR(ctbc0.ngay) = YEAR((SELECT phieudattieccuoi.ngayDaiTiec 
                                           FROM phieudattieccuoi 
                                           WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi)) 
              AND MONTH(ctbc0.ngay) = MONTH((SELECT phieudattieccuoi.ngayDaiTiec 
                                              FROM phieudattieccuoi 
                                              WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi));
        END IF;
    ELSE
        -- Cập nhật doanh thu trong baocaodoanhthu nếu không có báo cáo doanh thu
        UPDATE baocaodoanhthu as bcdt 
        SET bcdt.tongDoanhThu = 
            (SELECT SUM(hoadon.tongTienHoaDon) 
             FROM hoadon 
             WHERE YEAR(hoadon.ngayThanhToan) = YEAR((SELECT phieudattieccuoi.ngayDaiTiec 
                                                       FROM phieudattieccuoi 
                                                       WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi)) 
               AND MONTH((SELECT phieudattieccuoi.ngayDaiTiec 
                          FROM phieudattieccuoi 
                          WHERE hoadon.maTiecCuoi = phieudattieccuoi.maTiecCuoi)) 
                   = MONTH(NEW.ngayThanhToan))
        WHERE bcdt.nam = YEAR((SELECT phieudattieccuoi.ngayDaiTiec 
                               FROM phieudattieccuoi 
                               WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi)) 
          AND bcdt.thang = MONTH((SELECT phieudattieccuoi.ngayDaiTiec 
                                  FROM phieudattieccuoi 
                                  WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi));

        -- Kiểm tra và chèn vào ChiTietBaoCao nếu đúng
        IF (SELECT COUNT(*) 
            FROM hoadon 
            WHERE (SELECT phieudattieccuoi.ngayDaiTiec 
                   FROM phieudattieccuoi 
                   WHERE hoadon.maTiecCuoi = phieudattieccuoi.maTiecCuoi) 
                = (SELECT phieudattieccuoi.ngayDaiTiec 
                   FROM phieudattieccuoi 
                   WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi)) = 1 THEN

            INSERT INTO ChiTietBaoCao (maBaoCao, ngay, soLuongTiec, doanhThu, tiLe)
            VALUES (
                (SELECT bcdt.maBaoCao 
                 FROM baocaodoanhthu as bcdt 
                 WHERE bcdt.thang = MONTH((SELECT phieudattieccuoi.ngayDaiTiec 
                                           FROM phieudattieccuoi 
                                           WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi)) 
                 AND bcdt.nam = YEAR((SELECT phieudattieccuoi.ngayDaiTiec 
                                      FROM phieudattieccuoi 
                                      WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi))),
                (SELECT phieudattieccuoi.ngayDaiTiec 
                 FROM phieudattieccuoi 
                 WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi),
                1,
                NEW.tongTienHoaDon,
                1
            );

            UPDATE chitietbaocao as ctbc0 
            SET ctbc0.tiLe = ctbc0.doanhThu / 
                (SELECT SUM(ctbc.doanhThu) 
                 FROM chitietbaocao as ctbc 
                 WHERE YEAR(ctbc.ngay) = YEAR(ctbc0.ngay) 
                   AND MONTH(ctbc.ngay) = MONTH(ctbc0.ngay)) 
            WHERE YEAR(ctbc0.ngay) = YEAR((SELECT phieudattieccuoi.ngayDaiTiec 
                                           FROM phieudattieccuoi 
                                           WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi)) 
              AND MONTH(ctbc0.ngay) = MONTH((SELECT phieudattieccuoi.ngayDaiTiec 
                                              FROM phieudattieccuoi 
                                              WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi));
        ELSE
            UPDATE chitietbaocao as ctbc0 
            SET ctbc0.soLuongTiec = 
                (SELECT COUNT(*) 
                 FROM hoadon 
                 WHERE (SELECT phieudattieccuoi.ngayDaiTiec 
                        FROM phieudattieccuoi 
                        WHERE hoadon.maTiecCuoi = phieudattieccuoi.maTiecCuoi) 
                   = (SELECT phieudattieccuoi.ngayDaiTiec 
                      FROM phieudattieccuoi 
                      WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi)),
                ctbc0.doanhThu = ctbc0.doanhThu + NEW.tongTienHoaDon 
            WHERE ctbc0.ngay = (SELECT phieudattieccuoi.ngayDaiTiec 
                                FROM phieudattieccuoi 
                                WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi);

            UPDATE chitietbaocao as ctbc0 
            SET ctbc0.tiLe = ctbc0.doanhThu / 
                (SELECT SUM(ctbc.doanhThu) 
                 FROM chitietbaocao as ctbc 
                 WHERE YEAR(ctbc.ngay) = YEAR(ctbc0.ngay) 
                   AND MONTH(ctbc.ngay) = MONTH(ctbc0.ngay)) 
            WHERE YEAR(ctbc0.ngay) = YEAR((SELECT phieudattieccuoi.ngayDaiTiec 
                                           FROM phieudattieccuoi 
                                           WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi)) 
              AND MONTH(ctbc0.ngay) = MONTH((SELECT phieudattieccuoi.ngayDaiTiec 
                                              FROM phieudattieccuoi 
                                              WHERE NEW.maTiecCuoi = phieudattieccuoi.maTiecCuoi));
        END IF;
    END IF;
END;


```


<div align="center">
<img width="452" alt="image" src="https://github.com/user-attachments/assets/549dc5d6-c296-43a3-8614-1f0dd89e855c">
</div>




