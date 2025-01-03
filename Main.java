import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.lang.String;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GovermentProject[] projectsGov = new GovermentProject[10];
        PrivateProject[] projectsPri = new PrivateProject[10];
        TimPekerja[] teamMembers = new TimPekerja[10];

        TimPekerja[] DevTenagaKerja = new TimPekerja[10];
        TimPekerja[] DevKontraktor = new TimPekerja[10];
        TimPekerja[] DevPengawas = new TimPekerja[10];

        Service[] services = new Service[10];
        Material[] materials = new Material[10];
        LaporanProyek[] laporanProyeks = new LaporanProyek[10];

        int projectCount = 0;
        int teamMemberCount = 0;
        int serviceCount = 0;
        int materialCount = 0;
        int laporanCount = 0;

        while (true) {
            System.out.println("+-----------------------------------+");
            System.out.println("|    MENU PENGELOLAAN PROYEK        |");
            System.out.println("+-----------------------------------+");
            System.out.println("1. Buat Proyek Pemerintah");
            System.out.println("2. Buat Proyek Private");
            System.out.println("3. Tambahkan Team Member");
            System.out.println("4. Tugaskan Team Member ke Proyek");
            System.out.println("5. Buat Service");
            System.out.println("6. Tampilkan Proyek");
            System.out.println("7. Tampilkan Team Member");
            System.out.println("8. Tampilkan Service");
            System.out.println("9. Kelola Material");
            System.out.println("10. Buat Laporan Proyek");
            System.out.println("11. Tampilkan Laporan Proyek");
            System.out.println("12. Keluar");
            System.out.print("Masukkan Pilih Anda: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Masukkan ID Proyek: ");
                    String govProjId = scanner.nextLine();
                    System.out.print("Masukkan Nama Proyek: ");
                    String govProjName = scanner.nextLine();
                    System.out.print("Masukkan Lokasi: ");
                    String govLocation = scanner.nextLine();
                    System.out.print("Masukkan Anggaran: ");
                    int govBudget = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Masukkan Status: ");
                    String govStatus = scanner.nextLine();
                    System.out.print("Masukkan Nama Institusi: ");
                    String institutionName = scanner.nextLine();

                    projectsGov[projectCount] = new GovermentProject(govProjId, govProjName, govLocation, govBudget, govStatus, institutionName);
                    projectCount++;
                    break;
                case 2:
                    System.out.print("Masukkan ID Proyek: ");
                    String privProjId = scanner.nextLine();
                    System.out.print("Masukkan Nama Proyek: ");
                    String privProjName = scanner.nextLine();
                    System.out.print("Masukkan Lokasi: ");
                    String privLocation = scanner.nextLine();
                    System.out.print("Masukkan Anggaran: ");
                    int privBudget = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Masukkan Status: ");
                    String privStatus = scanner.nextLine();
                    System.out.print("Masukkan Nama Institution: ");
                    String clientName = scanner.nextLine();

                    projectsPri[projectCount] = new PrivateProject(privProjId, privProjName, privLocation, privBudget, privStatus, clientName);
                    projectCount++;
                    break;
                case 3:
                    System.out.println("1. Tambahkan Tenaga Kerja");
                    System.out.println("2. Tambahkan Pengawas Proyek");
                    System.out.println("3. Tambahkan Kontraktor");
                    System.out.print("Masukkan Pilihan Anda: ");
                    int memberChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    System.out.print("Masukkan ID: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Masukkan Nama: ");
                    String memberName = scanner.nextLine();
                    System.out.print("Masukkan Tanggal Masuk (YYYY-MM-DD): ");
                    String dateInput = scanner.nextLine();

                    String shiftKerja = "";
                    String proyekDiawasi = "";
                    String keahlian = "";

                    if (memberChoice == 1) {
                        System.out.println("Masukkan Shift Kerja (Siang/Malam): ");
                        shiftKerja = scanner.nextLine();
                    } else if (memberChoice == 2) {
                        System.out.println("Masukkan Proyek Diawasi (Ada/Tidak): ");
                        proyekDiawasi = scanner.nextLine();
                    } else if (memberChoice == 3) {
                        System.out.println("Masukkan Keahlian (Bebas): ");
                        keahlian = scanner.nextLine();
                    }

                    try {
                        // Konversi string menjadi Date
                        Date dateOfJoining = Date.valueOf(dateInput);

                        // Format Date ke String
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedDate = dateFormat.format(dateOfJoining);

                        switch (memberChoice) {
                            case 1:
                                DevTenagaKerja[teamMemberCount] = new TenagaKerja(memberId, memberName, dateOfJoining, shiftKerja);
                                break;
                            case 2:
                                DevPengawas[teamMemberCount] = new PengawasProyek(memberId, memberName, dateOfJoining, proyekDiawasi);
                                break;
                            case 3:
                                DevKontraktor[teamMemberCount] = new Kontraktor(memberId, memberName, dateOfJoining, keahlian);
                                break;
                            default:
                                System.out.println("Pilihan Invalid.");
                                continue;
                        }
                    teamMemberCount++;
                        System.out.println("Anggota berhasil ditambahkan!");
                    break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Format tanggal salah! Harus menggunakan format YYYY-MM-DD.");
                    }
                case 4:
                    System.out.print("Masukkan ID Team Member : ");
                    String tMemberId = scanner.nextLine();
                    System.out.print("Masukkan ID Proyek: ");
                    String tProjectId = scanner.nextLine();

                    for (TimPekerja member : DevTenagaKerja) {
                        if (member != null && member.getId().equals(tMemberId)) {
                            member.TambahProyek(tProjectId);
                            break;
                        }
                    }
                    for (TimPekerja member : DevKontraktor) {
                        if (member != null && member.getId().equals(tMemberId)) {
                            member.TambahProyek(tProjectId);
                            break;
                        }
                    }
                    for (TimPekerja member : DevPengawas) {
                        if (member != null && member.getId().equals(tMemberId)) {
                            member.TambahProyek(tProjectId);
                            break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("1. Buat Jasa Architect ");
                    System.out.println("2. Buat Jasa Teknik Sipil ");
                    System.out.print("Masukkan Pilihan Anda: ");
                    int serviceChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    System.out.print("Masukkan Nama Ahli: ");
                    String expertName = scanner.nextLine();
                    System.out.print("Masukkan Tarif per Desain: ");
                    double ratePerDesign = scanner.nextDouble();
                    scanner.nextLine(); // consume newline

                    switch (serviceChoice) {
                        case 1:
                            services[serviceCount] = new ArchitectService(expertName, ratePerDesign);
                            break;
                        case 2:
                            services[serviceCount] = new CivilService(expertName, ratePerDesign);
                            break;
                        default:
                            System.out.println("Pilihan Tidak Valid.");
                            continue;
                    }
                    serviceCount++;
                    break;
                case 6:
                    System.out.println("1. Proyek Pemerintah");
                    System.out.println("2. Proyek Pribadi");
                    int pilih = scanner.nextInt();
                    if (pilih == 1) {
                        for (GovermentProject govermentProject : projectsGov) {
                            if (govermentProject != null) {
                                govermentProject.displayProjectGovernment();
                            }
                        }
                    } else if (pilih == 2) {
                        for (PrivateProject project : projectsPri) {
                            if (project != null) {
                                project.displayProjectPrivate();
                            }
                        }
                    }
                    break;
                case 7:
                    System.out.println("=== Daftar Tenaga Kerja ===");
                    for (TimPekerja member : DevTenagaKerja) {
                        if (member != null) {
                            System.out.println("ID: " + member.getId() + ",\nNama: " + member.getNama() +
                                    ",\nID Project : " + member.getIdProyek() +
                                    ",\nTanggal Masuk: " + member.getTanggalMasuk());
                            if (member.getIdProyek() != null) {
                                System.out.println("Masukkan Durasi: ");
                                int durasi = scanner.nextInt();
                                System.out.println("Gaji :" + member.Gaji(durasi) );
                            } else {
                                System.out.println("Gaji : Rp. 0");
                            }
                        }

                    }
                    System.out.println("=== Daftar Kontraktor ===");
                    for (TimPekerja member : DevKontraktor) {
                        if (member != null) {
                            System.out.println("ID: " + member.getId() + ",\nNama: " + member.getNama() +
                                    ",\nID Project : " + member.getIdProyek() +
                                    ",\nTanggal Masuk: " + member.getTanggalMasuk());
                            if (member.getIdProyek() != null) {
                                System.out.println("Masukkan Durasi: ");
                                int durasi = scanner.nextInt();
                                System.out.println("Gaji :" + member.Gaji(durasi) );
                            } else {
                                System.out.println("Gaji : Rp. 0");
                            }
                        }
                    }
                    System.out.println("=== Daftar Pengawas ===");
                    for (TimPekerja member : DevPengawas) {
                        if (member != null) {
                            System.out.println("ID: " + member.getId() + ",\nNama: " + member.getNama() +
                                    ",\nID Project : " + member.getIdProyek() +
                                    ",\nTanggal Masuk: " + member.getTanggalMasuk());
                            if (member.getIdProyek() != null) {
                                System.out.println("Masukkan Durasi: ");
                                int durasi = scanner.nextInt();
                                System.out.println("Gaji :" + member.Gaji(durasi) );
                            } else {
                                System.out.println("Gaji : Rp. 0");
                            }
                        }
                    }
                    break;
                case 8:
                    for (Service serv : services) {
                        if (serv != null) {
                            serv.displayService();
                        }
                    }
                    break;
                case 9:
                    System.out.println("1. Tambahkan Material");
                    System.out.println("2. Kurangi Material");
                    System.out.println("3. Update Harga Material ");
                    System.out.println("4. Tampilkan Material");
                    System.out.print("Masukkan Pilihan Anda: ");
                    int materialChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    System.out.print("Masukkan Nama Material: ");
                    String materialName = scanner.nextLine();

                    Material foundMaterial = null;
                    for (Material mat : materials) {
                        if (mat != null && mat.getNamaMaterial().equals(materialName)) {
                            foundMaterial = mat;
                            break;
                        }
                    }

                    if (materialChoice != 1 && foundMaterial == null) {
                        System.out.println("Material Tidak Ditemukan.");
                        continue;
                    }

                    switch (materialChoice) {
                        case 1:
                            System.out.print("Masukkan Stok: ");
                            int stock = scanner.nextInt();
                            System.out.print("Masukkan Harga: ");
                            int price = scanner.nextInt();
                            materials[materialCount] = new Material(materialName, stock, price);
                            materialCount++;
                            break;
                        case 2:
                            System.out.print("Masukkan Jumlah yang akan Dikurangi: ");
                            int reduceQty = scanner.nextInt();
                            foundMaterial.reduceMaterial(reduceQty);
                            break;
                        case 3:
                            System.out.print("Masukkan Harga Baru: ");
                            int newPrice = scanner.nextInt();
                            foundMaterial.updatePriceMaterial(newPrice);
                            break;
                        case 4:
                            foundMaterial.displayMaterial();
                            break;
                        default:
                            System.out.println("Pilihan Tidak Valid.");
                    }
                    break;
                case 10:
                    System.out.print("Masukkan ID Proyek : ");
                    String reportProjId = scanner.nextLine();
                    Proyek foundProject = null;

                    for (GovermentProject govermentProject : projectsGov) {
                        if (govermentProject != null) {
                            govermentProject.displayProjectGovernment();
                        }
                    }
                    for (PrivateProject project : projectsPri) {
                        if (project != null) {
                            project.displayProjectPrivate();
                        }
                    }

                    if (foundProject == null) {
                        System.out.println("Proyek Tidak Ditemukan.");
                        continue;
                    }

                    System.out.print("Masukkan Tanggal Laporan (YYYY-MM-DD): ");
                    String reportDate = scanner.nextLine();
                    System.out.print("Masukkan Keterangan Proyek : ");
                    String projectDetails = scanner.nextLine();

                    laporanProyeks[laporanCount] = new LaporanProyek(foundProject, reportDate, projectDetails);
                    laporanCount++;
                    break;
                case 11:
                    for (LaporanProyek laporan : laporanProyeks) {
                        if (laporan != null) {
                            laporan.DisplayProject();
                        }
                    }
                    break;
                case 12:
                    System.out.println("Mengeluarkan dari Program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan Invalid. Silahkan Coba Lagi.");
            }
        }
    }
}