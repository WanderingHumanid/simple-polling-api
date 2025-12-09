# Simple Polling API

A web-based polling application built with Spring Boot that allows users to create, vote on, and manage polls with an intuitive dashboard interface.

## ✨ Features

- 🗳️ **Create & Manage Polls** - Users can create their own polls with custom options
- 📊 **Real-time Voting** - Vote on polls and see instant results with percentage bars
- 📱 **User Dashboard** - Three-tab interface: All Polls, My Votes, and My Polls
- 🎨 **Visual Feedback** - Grey highlight on option selection with smooth animations
- 🔐 **Secure Authentication** - User registration and login with role-based access
- 👥 **Admin Panel** - Administrators can manage all polls and users
- ✏️ **Edit & Delete** - Poll creators can edit or remove their polls

## 🚀 Quick Start

### Prerequisites
- Java 17+
- MySQL 8.0+
- Maven 3.6+

### Setup

1. **Create Database**
```sql
CREATE DATABASE polldb;
```

2. **Configure Database** - Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/polldb
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. **Run Application**
```bash
mvnw spring-boot:run
```

4. **Access** - Open http://localhost:8080 and register/login

## 📖 Usage

- **Create Poll**: Navigate to "My Polls" tab → Enter question and options → Click "Create Poll"
- **Vote**: Go to "All Polls" tab → Select an option → Click "Submit Vote"
- **View Results**: Check "My Votes" tab to see polls you've voted on
- **Manage**: Edit or delete your polls from the "My Polls" tab

## 🛠️ Tech Stack

- **Backend**: Spring Boot 3.3.5, Spring Security, Spring Data JPA
- **Frontend**: Thymeleaf, Bootstrap 5
- **Database**: MySQL 8.0
- **Build Tool**: Maven

## 📂 Project Structure

```
src/main/java/com/example/valid/
├── controllers/        # REST controllers
├── models/            # JPA entities
├── repositories/      # Data access layer
└── security/          # Authentication & authorization

src/main/resources/
├── templates/         # Thymeleaf HTML templates
└── application.properties
```

## 📚 Documentation

- [Quick Start Guide](QUICK_START_GUIDE.md) - Detailed usage instructions
- [Architecture](ARCHITECTURE.md) - System design and flow diagrams
- [Implementation Details](USER_POLL_FEATURE_SUMMARY.md) - Technical overview
- [Visual Feedback Feature](VISUAL_FEEDBACK_FEATURE.md) - UI enhancement details

## 🔒 Security

- Password encryption with BCrypt
- CSRF protection enabled
- Role-based access control (USER/ADMIN)
- Ownership validation for poll operations

## 📄 License

This project is created for educational purposes.
```

---

## 🐛 Troubleshooting

### Build Issues
**Problem**: `mvnw: command not found`
**Solution**: Use `./mvnw` (Linux/Mac) or `mvnw.cmd` (Windows)

**Problem**: Compilation errors
**Solution**: Ensure Java 17+ is installed and `JAVA_HOME` is set

### Database Issues
**Problem**: `Access denied for user`
**Solution**: Check MySQL credentials in `application.properties`

**Problem**: `Unknown database 'polldb'`
**Solution**: Create the database: `CREATE DATABASE polldb;`

**Problem**: `Table doesn't exist`
**Solution**: Set `spring.jpa.hibernate.ddl-auto=update` and restart

### Runtime Issues
**Problem**: 404 on `/user/dashboard`
**Solution**: Ensure you're logged in and UserController is loaded

**Problem**: "You are not authorized"
**Solution**: You're trying to edit/delete someone else's poll

**Problem**: Polls not showing after creation
**Solution**: Refresh the page or check browser console for errors

### Frontend Issues
**Problem**: Tabs not switching
**Solution**: Ensure Bootstrap JS is loaded (check browser console)

**Problem**: Modal not showing
**Solution**: Clear browser cache and reload

**Problem**: Styling looks wrong
**Solution**: Check if CSS file exists at `/css/styles.css`

---

## 📚 Additional Resources

- **Technical Details**: See [USER_POLL_FEATURE_SUMMARY.md](USER_POLL_FEATURE_SUMMARY.md)
- **User Guide**: See [QUICK_START_GUIDE.md](QUICK_START_GUIDE.md)
- **Migration**: See [migration_script.sql](migration_script.sql)
- **Spring Boot Docs**: https://spring.io/projects/spring-boot
- **Thymeleaf Docs**: https://www.thymeleaf.org/

---

## 🔮 Future Enhancements

### Planned Features
- [ ] Poll expiration dates
- [ ] Private/public poll visibility
- [ ] Poll categories and tags
- [ ] Search and filter functionality
- [ ] Export results to CSV/PDF
- [ ] Poll sharing via unique links
- [ ] Real-time vote updates (WebSocket)
- [ ] User profile pages
- [ ] Comment section per poll
- [ ] Multiple choice voting (select multiple options)
- [ ] Weighted voting system
- [ ] Poll templates
- [ ] Analytics dashboard
- [ ] Email notifications
- [ ] Mobile app

### Performance Optimizations
- [ ] Add caching for frequently accessed polls
- [ ] Implement pagination for large poll lists
- [ ] Optimize database queries with indexes
- [ ] Add lazy loading for poll results
- [ ] Compress frontend assets

### Security Enhancements
- [ ] Rate limiting for poll creation
- [ ] CAPTCHA for registration
- [ ] Two-factor authentication
- [ ] Password strength requirements
- [ ] Session timeout configuration
- [ ] Audit logging

---

## 👥 Contributing

To contribute to this project:
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Write/update tests
5. Submit a pull request

---

## 📄 License

This project is licensed under the MIT License.

---

## 📞 Support

For issues or questions:
- Create an issue in the repository
- Contact: your-email@example.com
- Documentation: See MD files in project root

---

**Version**: 1.0.0  
**Last Updated**: December 9, 2025  
**Authors**: Development Team  
**Status**: Production Ready ✅
