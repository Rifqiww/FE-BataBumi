# 📱 Material Icons Reference untuk Konsultan Konstruksi

## ✅ Ikon yang Tersedia dan Digunakan

### **Template Konsultasi:**
- `Icons.Default.AttachMoney` - 💰 Estimasi Biaya
- `Icons.Default.GppGood` - 🛡️ Keselamatan Kerja  
- `Icons.Default.Build` - 🔨 Pemilihan Material
- `Icons.Default.Event` - 📅 Perencanaan Proyek
- `Icons.Default.Warning` - ⚠️ Manajemen Risiko
- `Icons.Default.CheckCircle` - ✅ Kontrol Kualitas

## 🔧 Ikon Alternatif yang Tersedia

### **Untuk Estimasi Biaya:**
- `Icons.Default.AttachMoney` ✅ (digunakan)
- `Icons.Default.MonetizationOn`
- `Icons.Default.Paid`
- `Icons.Default.AccountBalance`

### **Untuk Keselamatan Kerja:**
- `Icons.Default.GppGood` ✅ (digunakan)
- `Icons.Default.Security`
- `Icons.Default.Shield`
- `Icons.Default.HealthAndSafety`

### **Untuk Pemilihan Material:**
- `Icons.Default.Build` ✅ (digunakan)
- `Icons.Default.Construction`
- `Icons.Default.Handyman`
- `Icons.Default.HomeRepairService`

### **Untuk Perencanaan Proyek:**
- `Icons.Default.Event` ✅ (digunakan)
- `Icons.Default.Schedule`
- `Icons.Default.CalendarToday`
- `Icons.Default.Timeline`

### **Untuk Manajemen Risiko:**
- `Icons.Default.Warning` ✅ (digunakan)
- `Icons.Default.Error`
- `Icons.Default.ReportProblem`
- `Icons.Default.Dangerous`

### **Untuk Kontrol Kualitas:**
- `Icons.Default.CheckCircle` ✅ (digunakan)
- `Icons.Default.Verified`
- `Icons.Default.TaskAlt`
- `Icons.Default.Quality`

## 🎨 Ikon Tambahan untuk UI

### **Navigation & Actions:**
- `Icons.Default.Add` - Tambah chat baru
- `Icons.Default.Clear` - Hapus chat
- `Icons.Default.Send` - Kirim pesan
- `Icons.Default.Menu` - Menu
- `Icons.Default.Search` - Pencarian

### **Status & Feedback:**
- `Icons.Default.CheckCircle` - Berhasil
- `Icons.Default.Error` - Error
- `Icons.Default.Info` - Informasi
- `Icons.Default.Help` - Bantuan

### **Construction Specific:**
- `Icons.Default.Home` - Rumah/Perumahan
- `Icons.Default.Business` - Komersial
- `Icons.Default.Road` - Infrastruktur
- `Icons.Default.Build` - Konstruksi
- `Icons.Default.Engineering` - Teknik

## 📋 Cara Menggunakan Ikon

```kotlin
// Import ikon yang diperlukan
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

// Gunakan dalam komponen
Icon(
    imageVector = Icons.Default.AttachMoney,
    contentDescription = "Estimasi Biaya",
    modifier = Modifier.size(24.dp),
    tint = MaterialTheme.colorScheme.primary
)
```

## ⚠️ Ikon yang Tidak Tersedia

Ikon berikut **TIDAK TERSEDIA** di `Icons.Default`:
- ❌ `Icons.Default.Calculate`
- ❌ `Icons.Default.Security` 
- ❌ `Icons.Default.Schedule`

Gunakan alternatif yang tersedia seperti yang tercantum di atas.

## 🔄 Update Ikon

Jika ingin mengubah ikon, pastikan menggunakan ikon yang tersedia di `Icons.Default` atau tambahkan dependency untuk ikon tambahan jika diperlukan.

---
*Referensi ini membantu memilih ikon yang tepat untuk UI Konsultan Konstruksi AI* 🏗️
