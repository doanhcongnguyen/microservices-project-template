<template>
  <div>
    <el-dialog :title="isUpdateMode ? $t('settings.user.updateTitle') : $t('settings.user.createTitle')" :before-close="closeDialog" :visible.sync="dialogFormVisible">
      <el-form ref="form" :rules="rules" :model="form" label-position="right" label-width="14rem" size="mini">
        <el-form-item :label="$t('settings.user.name')" prop="username">
          <el-input v-model="form.username" class="form-item" />
        </el-form-item>
        <el-form-item :label="$t('settings.user.fullName')" prop="fullName">
          <el-input v-model="form.fullName" class="form-item" />
        </el-form-item>
        <el-form-item v-if="isUpdateMode" :label="$t('settings.user.changePass')">
          <el-checkbox v-model="form.changePass" />
        </el-form-item>
        <el-form-item v-if="!isUpdateMode || (isUpdateMode && form.changePass)" :label="$t('settings.user.password')">
          <el-input v-model="form.password" type="password" class="form-item" />
        </el-form-item>
        <el-form-item :label="$t('settings.user.phoneNumber')">
          <el-input v-model="form.telephone" class="form-item" />
        </el-form-item>
        <el-form-item :label="$t('settings.user.email')">
          <el-input v-model="form.email" class="form-item" />
        </el-form-item>
        <el-form-item>
          <el-button @click="closeDialog">{{ $t('global.button.cancel') }}</el-button>
          <el-button type="primary" @click="save">{{ isUpdateMode ? $t('global.button.update') :$t('global.button.save') }}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { create, update } from '@/api/user-management'
import { showSuccessMessage } from '@/utils/commons'

export default {
  props: {
    dialogFormVisible: {
      type: Boolean,
      required: true
    },
    isUpdateMode: {
      type: Boolean,
      required: true
    },
    form: {
      default: function() { return {} },
      type: Object,
      required: true
    }
  },
  data() {
    return {
      rules: {
        'username': [{ required: true, message: this.$i18n.t('validate.required', { name: this.$i18n.t('settings.user.name') }), trigger: 'blur' }],
        'fullName': [{ required: true, message: this.$i18n.t('validate.required', { name: this.$i18n.t('settings.user.fullName') }), trigger: 'blur' }]
      }
    }
  },
  methods: {
    save() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.onSubmit()
        }
      })
    },
    async onSubmit() {
      const formData = this.createFormData()
      const response = this.isUpdateMode ? await update(formData) : await create(formData)
      this.showResultMessage(response)
    },
    createFormData() {
      return Object.assign({}, this.form)
    },
    showResultMessage(response) {
      showSuccessMessage()
      this.closeDialog()
    },
    closeDialog() {
      this.$emit('close-dialog')
    }
  }
}
</script>
