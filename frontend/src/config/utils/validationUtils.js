export const validateRegisterUtils = (formData) => {
    const {fullName, username, password, confirmPassword, businessName, phone} = formData;
    const errors = [];

    if (!fullName.trim()) {
        errors.push('Full Name  es requerido');
    }

    if (!username.trim()) {
        errors.push('Email es requerido');
    } else {
        const regexEmail = /^\S+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (!regexEmail.test(username)) {
            errors.push('Email inválido');
        }
    }

    if (!password.trim()) {
        errors.push('Password  es requerido');
    } else if (password.length < 6) {
        errors.push('La contraseña debe tener al menos 6 caracteres');
    } else {
        const regexPassword = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{6,}$/;
        if (!regexPassword.test(password)) {
            errors.push('La contraseña debe contener al menos una mayúscula, una minúscula y un número');
        }
    }

    if (password !== confirmPassword) {
        errors.push('Contraseñas no coinciden');
    }

    if (!businessName.trim() || businessName.length < 3) {
        errors.push('Empresa es requerido');
    }

    if (!phone.trim()) {
        errors.push('Teléfono es requerido');
    } else {
        const regexPhone = /^\d{10}$/;
        if (!regexPhone.test(phone)) {
            errors.push('Teléfono inválido');
        }
    }

    return errors;
}

// if (!fullName || !email || !password || !confirmPassword || !phone || !businessName) return alert('Por favor, completa todos los campos');
// if(password.length < 8) return alert('La contraseña debe tener al menos 8 caracteres');
// if(phone.length < 10) return alert('El teléfono debe tener al menos 10 caracteres');
// if(businessName.length < 3) return alert('El nombre de la empresa debe tener al menos 3 caracteres');
// if (email.length < 3) return alert('El nombre de usuario debe tener al menos 3 caracteres');
// // regex para validar que el teléfono sea un número
// const regex = /^[0-9]+$/;
// if(!regex.test(phone)) return alert('El teléfono debe ser un número');
// const regexUsername = /^\S+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
// if (!regexUsername.test(email)) return alert('El nombre de usuario no puede tener espacios o debe ser un email válido');
// // regex para validar que password tenga al menos una mayúscula, una minúscula y un número
// const regex4 = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
// if(!regex4.test(password)) return alert('La contraseña debe tener al menos una mayúscula, una minúscula y un número');
// if (password !== confirmPassword) return alert('Las contraseñas no coinciden');