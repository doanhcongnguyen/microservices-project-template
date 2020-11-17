<template>
  <div>
    <el-dialog :title="isUpdateMode ? $t('device.updateTitle') : $t('device.createTitle')" :before-close="closeDialog" :visible.sync="dialogFormVisible">
      <el-form ref="form" :rules="rules" :model="form" label-position="right" label-width="14rem" size="mini">
        <el-form-item :label="$t('device.serial')" prop="serial">
          <el-input v-model="form.serial" :disabled="isUpdateMode" class="form-item" />
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
import { create, update } from '@/api/device-management'
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
        'serial': [{ required: true, message: this.$i18n.t('validate.required', { name: this.$i18n.t('device.serial') }), trigger: 'blur' }]
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
