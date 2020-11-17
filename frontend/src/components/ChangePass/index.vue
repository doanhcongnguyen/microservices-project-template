<template>
  <div>
    <el-dialog title="Change passord" :before-close="closeDialog" :visible.sync="changePassFormVisible">
      <el-form ref="form" :rules="rules" :model="form" label-position="right" label-width="14rem" size="mini">
        <el-form-item :label="$t('settings.user.name')">
          <el-input v-model="user" :disabled="true" class="form-item" />
        </el-form-item>
        <el-form-item :label="$t('settings.user.newPassword')" prop="newPassword">
          <el-input v-model="form.newPassword" type="password" class="form-item" />
        </el-form-item>
        <el-form-item>
          <el-button @click="closeDialog">{{ $t('global.button.cancel') }}</el-button>
          <el-button type="primary" @click="save">{{ $t('global.button.save') }}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { changePass } from '@/api/auth'
import { showSuccessMessage } from '@/utils/commons'

export default {
  props: {
    changePassFormVisible: {
      type: Boolean,
      required: true
    },
    user: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      form: {},
      rules: {
        'newPassword': [{ required: true, message: this.$i18n.t('validate.required', { name: this.$i18n.t('settings.user.newPassword') }), trigger: 'blur' }]
      }
    }
  },
  mounted() {
    this.form = {}
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
      const response = await changePass(this.form)
      this.showResultMessage(response)
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
